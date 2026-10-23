
        package com.vendingmachine;

import com.vendingmachine.application.service.PurchaseService;
import com.vendingmachine.application.service.VendingMachineService;

import com.vendingmachine.common.ClockProvider;
import com.vendingmachine.common.IdGenerator;

import com.vendingmachine.dispensing.DispensingService;
import com.vendingmachine.dispensing.HardwareDispenser;

import com.vendingmachine.domain.inventory.Inventory;
import com.vendingmachine.domain.inventory.Slot;
import com.vendingmachine.domain.inventory.SlotId;

import com.vendingmachine.domain.machine.MachineId;
import com.vendingmachine.domain.machine.MachineStatus;
import com.vendingmachine.domain.machine.VendingMachine;

import com.vendingmachine.domain.product.Product;
import com.vendingmachine.domain.product.ProductCategory;
import com.vendingmachine.domain.product.ProductId;

import com.vendingmachine.infrastructure.hardware.MockHardwareController;
import com.vendingmachine.infrastructure.persistence.InMemoryInventoryRepository;
import com.vendingmachine.infrastructure.persistence.InMemoryProductRepository;
import com.vendingmachine.infrastructure.persistence.InMemoryTransactionRepository;

import com.vendingmachine.payment.gateway.CashPaymentGateway;
import com.vendingmachine.payment.service.ChangeCalculator;
import com.vendingmachine.payment.service.PaymentService;
import com.vendingmachine.payment.strategy.CashPaymentStrategy;

import java.util.List;
import java.util.Map;

public class VendingMachineApplication {

    public static void main(String[] args) {

        // 1. Create product
        Product cola = new Product(
                new ProductId("COLA"),
                "Cola",
                1500,
                ProductCategory.DRINK
        );

        // 2. Initialize inventory
        Inventory inventory = new Inventory();

        inventory.addSlot(
                new Slot(
                        new SlotId("A1"),
                        cola,
                        5
                )
        );

        // 3. Initialize repositories
        var inventoryRepository =
                new InMemoryInventoryRepository(inventory);

        var productRepository =
                new InMemoryProductRepository(List.of(cola));

        var transactionRepository =
                new InMemoryTransactionRepository();

        // 4. Initialize hardware
        var hardwareController =
                new MockHardwareController();

        // HardwareDispenser implements Dispenser
        var dispenser =
                new HardwareDispenser(hardwareController);

        var dispensingService =
                new DispensingService(dispenser);

        // 5. Initialize payment service
        var paymentService = new PaymentService(
                Map.of(
                        "CASH",
                        new CashPaymentStrategy(
                                new CashPaymentGateway()
                        )
                ),
                new ChangeCalculator()
        );

        // 6. Initialize vending machine
        var machine = new VendingMachine(
                new MachineId("VM-001"),
                MachineStatus.ACTIVE
        );

        // 7. Initialize application services
        var machineService = new VendingMachineService(
                machine,
                inventoryRepository,
                productRepository
        );

        var purchaseService = new PurchaseService(
                machine,
                inventoryRepository,
                productRepository,
                transactionRepository,
                paymentService,
                dispensingService,
                new IdGenerator(),
                new ClockProvider()
        );

        // 8. Insert ₹20
        machineService.insertMoney(2000);

        // 9. Select product
        machineService.selectProduct(
                new ProductId("COLA")
        );

        // 10. Purchase product
        var result = purchaseService.purchase(
                "demo-request-001",
                "CASH"
        );

        // 11. Print purchase result
        System.out.println("===== VENDING MACHINE RESULT =====");
        System.out.println(result);

        // 12. Print remaining stock
        var remainingStock = inventoryRepository
                .findBySlotId(new SlotId("A1"))
                .orElseThrow()
                .getQuantity();

        System.out.println("Remaining stock: " + remainingStock);
    }
}