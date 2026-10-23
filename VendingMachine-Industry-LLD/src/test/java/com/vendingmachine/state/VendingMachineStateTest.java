package com.vendingmachine.state;

import com.vendingmachine.domain.machine.*;
import com.vendingmachine.domain.product.ProductId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VendingMachineStateTest {
    @Test void transitionsFromIdleToCreditToSelected() {
        VendingMachine machine = new VendingMachine(new MachineId("VM"), MachineStatus.ACTIVE);
        assertEquals("IDLE", machine.getState().name());
        machine.insertMoney(100);
        assertEquals("HAS_CREDIT", machine.getState().name());
        machine.selectProduct(new ProductId("P1"));
        assertEquals("PRODUCT_SELECTED", machine.getState().name());
    }
}
