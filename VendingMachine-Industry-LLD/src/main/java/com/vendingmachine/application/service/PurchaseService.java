package com.vendingmachine.application.service;

import com.vendingmachine.common.ClockProvider;
import com.vendingmachine.common.IdGenerator;
import com.vendingmachine.dispensing.*;
import com.vendingmachine.domain.exception.*;
import com.vendingmachine.domain.inventory.*;
import com.vendingmachine.domain.machine.VendingMachine;
import com.vendingmachine.domain.product.*;
import com.vendingmachine.domain.transaction.*;
import com.vendingmachine.infrastructure.event.*;
import com.vendingmachine.payment.service.PaymentService;
import com.vendingmachine.payment.strategy.PaymentResult;
import com.vendingmachine.repository.*;
import com.vendingmachine.application.result.PurchaseResult;
import com.vendingmachine.state.DispensingState;

public class PurchaseService {
    private final VendingMachine machine;
    private final InventoryRepository inventory;
    private final ProductRepository products;
    private final TransactionRepository transactions;
    private final PaymentService payments;
    private final DispensingService dispensing;
    private final IdGenerator ids;
    private final ClockProvider clock;
    private final DomainEventPublisher events;

    public PurchaseService(VendingMachine machine, InventoryRepository inventory, ProductRepository products,
            TransactionRepository transactions, PaymentService payments, DispensingService dispensing,
            IdGenerator ids, ClockProvider clock) {
        this(machine, inventory, products, transactions, payments, dispensing, ids, clock, new LoggingDomainEventPublisher());
    }
    public PurchaseService(VendingMachine machine, InventoryRepository inventory, ProductRepository products,
            TransactionRepository transactions, PaymentService payments, DispensingService dispensing,
            IdGenerator ids, ClockProvider clock, DomainEventPublisher events) {
        this.machine = machine; this.inventory = inventory; this.products = products; this.transactions = transactions;
        this.payments = payments; this.dispensing = dispensing; this.ids = ids; this.clock = clock; this.events = events;
    }

    public synchronized PurchaseResult purchase(String idempotencyKey, String paymentMethod) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) throw new IllegalArgumentException("Idempotency key required");
        var prior = transactions.findByIdempotencyKey(idempotencyKey);
        if (prior.isPresent()) {
            VendingTransaction tx = prior.get();
            return new PurchaseResult(tx.getId(), tx.getProductId(), tx.getPricePaise(), 0, tx.getStatus(),
                    "Idempotent replay: existing transaction status " + tx.getStatus());
        }

        machine.ensureAvailable();
        ProductId selectedId = machine.getSelectedProductId();
        if (selectedId == null) throw new InvalidStateException("Select a product before purchasing");
        Product product = products.findById(selectedId).orElseThrow(ProductNotFoundException::new);
        machine.getState().validatePurchase(machine, selectedId, product.pricePaise());

        InventoryItem item = inventory.findAvailableByProductId(selectedId).orElseThrow(OutOfStockException::new);
        long inserted = machine.getCreditPaise();
        VendingTransaction tx = new VendingTransaction(new TransactionId(ids.newId()), idempotencyKey,
                product.id().value(), product.pricePaise(), inserted, clock.now());
        tx.transitionTo(TransactionStatus.PAYMENT_PENDING);
        transactions.save(tx);

        PaymentResult paymentResult;
        try {
            paymentResult = payments.pay(paymentMethod, tx.getId().value(), product.pricePaise(), inserted);
            tx.transitionTo(TransactionStatus.PAYMENT_COMPLETED);
            events.publish(new PaymentCompletedEvent(tx.getId().value(), product.pricePaise(), paymentMethod, clock.now()));
        } catch (RuntimeException e) {
            tx.transitionTo(TransactionStatus.PAYMENT_FAILED);
            transactions.save(tx);
            throw e;
        }

        tx.transitionTo(TransactionStatus.DISPENSING);
        machine.setState(new DispensingState());
        DispensingResult result = dispensing.dispense(item.getSlotId());
        if (!result.successful()) {
            tx.transitionTo(TransactionStatus.REFUND_PENDING);
            transactions.save(tx);
            // Do not silently pretend the external refund succeeded. Recovery/reconciliation can retry it.
            machine.setStatus(com.vendingmachine.domain.machine.MachineStatus.OUT_OF_SERVICE);
            throw new DispensingFailedException(result.message() + "; refund/reconciliation required for transaction " + tx.getId());
        }

        inventory.dispenseOne(item.getSlotId());
        tx.transitionTo(TransactionStatus.COMPLETED);
        transactions.save(tx);
        machine.debitCredit(inserted);
        machine.refundAllCredit(); // clears selection and session credit after purchase
        machine.setState(new com.vendingmachine.state.IdleState());
        events.publish(new ProductDispensedEvent(machine.getId().value(), tx.getId().value(), product.id().value(), clock.now()));
        return new PurchaseResult(tx.getId(), product.name(), product.pricePaise(), paymentResult.changePaise(),
                tx.getStatus(), "Purchase completed");
    }
}
