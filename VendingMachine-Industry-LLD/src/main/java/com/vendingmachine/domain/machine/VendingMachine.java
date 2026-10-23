package com.vendingmachine.domain.machine;

import com.vendingmachine.domain.exception.MachineUnavailableException;
import com.vendingmachine.domain.exception.InsufficientFundsException;
import com.vendingmachine.domain.exception.InvalidStateException;
import com.vendingmachine.domain.payment.Money;
import com.vendingmachine.domain.product.ProductId;
import com.vendingmachine.state.IdleState;
import com.vendingmachine.state.VendingMachineState;

public class VendingMachine {
    private final MachineId id;
    private volatile MachineStatus status;
    private volatile VendingMachineState state;
    private long creditPaise;
    private ProductId selectedProductId;

    public VendingMachine(MachineId id, MachineStatus status) {
        if (id == null || status == null) throw new IllegalArgumentException("Invalid machine");
        this.id = id; this.status = status; this.state = new IdleState();
    }
    public synchronized void insertMoney(long paise) {
        ensureAvailable();
        if (paise <= 0) throw new IllegalArgumentException("Inserted money must be positive");
        creditPaise = Math.addExact(creditPaise, paise);
        state = state.afterMoneyInserted(this);
    }
    public synchronized void selectProduct(ProductId id) {
        ensureAvailable();
        if (id == null) throw new IllegalArgumentException("Product is required");
        selectedProductId = id;
        state = state.afterProductSelected(this);
    }
    public synchronized void requireSufficientCredit(long pricePaise) {
        if (creditPaise < pricePaise) throw new InsufficientFundsException();
    }
    public synchronized void debitCredit(long paise) {
        if (paise < 0 || paise > creditPaise) throw new IllegalArgumentException("Invalid debit");
        creditPaise -= paise;
    }
    public synchronized long refundAllCredit() {
        long amount = creditPaise; creditPaise = 0; selectedProductId = null; state = new IdleState(); return amount;
    }
    public synchronized void clearSelection() { selectedProductId = null; state = new IdleState(); }
    public synchronized void setState(VendingMachineState state) { this.state = state; }
    public synchronized void setStatus(MachineStatus status) { this.status = status; }
    public void ensureAvailable() {
        if (status != MachineStatus.ACTIVE) throw new MachineUnavailableException();
    }
    public MachineId getId() { return id; }
    public MachineStatus getStatus() { return status; }
    public VendingMachineState getState() { return state; }
    public synchronized long getCreditPaise() { return creditPaise; }
    public synchronized ProductId getSelectedProductId() { return selectedProductId; }
}
