package com.vendingmachine.application;

import com.vendingmachine.application.service.*;
import com.vendingmachine.domain.inventory.*;
import com.vendingmachine.domain.machine.*;
import com.vendingmachine.domain.product.*;
import com.vendingmachine.infrastructure.hardware.MockHardwareController;
import com.vendingmachine.infrastructure.persistence.*;
import com.vendingmachine.dispensing.*;
import com.vendingmachine.payment.service.*;
import com.vendingmachine.payment.strategy.*;
import com.vendingmachine.payment.gateway.CashPaymentGateway;
import com.vendingmachine.common.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseServiceTest {
    private record Fixture(VendingMachineService machineService, PurchaseService purchaseService,
                           InMemoryInventoryRepository inventoryRepository) {}
    private Fixture fixture(boolean hardwareFailure) {
        Product p = new Product(new ProductId("COLA"), "Cola", 1500, ProductCategory.DRINK);
        Inventory inventory = new Inventory();
        inventory.addSlot(new Slot(new SlotId("A1"), p, 2));
        var invRepo = new InMemoryInventoryRepository(inventory);
        var productRepo = new InMemoryProductRepository(List.of(p));
        var txRepo = new InMemoryTransactionRepository();
        var hw = new MockHardwareController(); hw.setShouldFail(hardwareFailure);
        var payment = new PaymentService(Map.of("CASH", new CashPaymentStrategy(new CashPaymentGateway())), new ChangeCalculator());
        var machine = new VendingMachine(new MachineId("VM"), MachineStatus.ACTIVE);
        var machineService = new VendingMachineService(machine, invRepo, productRepo);
        var purchase = new PurchaseService(machine, invRepo, productRepo, txRepo, payment,
                new DispensingService(new HardwareDispenser(hw)), new IdGenerator(), new ClockProvider(), event -> {});
        return new Fixture(machineService, purchase, invRepo);
    }
    @Test void completesPurchaseAndDecrementsStock() {
        var f = fixture(false);
        f.machineService.insertMoney(2000);
        f.machineService.selectProduct(new ProductId("COLA"));
        var result = f.purchaseService.purchase("key-1", "CASH");
        assertEquals("COMPLETED", result.status().name());
        assertEquals(1, f.inventoryRepository.findBySlotId(new SlotId("A1")).orElseThrow().getQuantity());
        assertEquals(500, result.changePaise());
    }
    @Test void rejectsInsufficientCredit() {
        var f = fixture(false);
        f.machineService.insertMoney(1000);
        f.machineService.selectProduct(new ProductId("COLA"));
        assertThrows(RuntimeException.class, () -> f.purchaseService.purchase("key-2", "CASH"));
    }
    @Test void failsSafelyWhenDispensingFails() {
        var f = fixture(true);
        f.machineService.insertMoney(2000);
        f.machineService.selectProduct(new ProductId("COLA"));
        assertThrows(RuntimeException.class, () -> f.purchaseService.purchase("key-3", "CASH"));
    }
}
