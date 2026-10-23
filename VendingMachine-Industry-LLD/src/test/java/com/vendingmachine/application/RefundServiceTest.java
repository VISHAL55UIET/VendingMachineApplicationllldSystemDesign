package com.vendingmachine.application;

import com.vendingmachine.application.service.RefundService;
import com.vendingmachine.domain.machine.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RefundServiceTest {
    @Test void returnsInsertedCreditOnCancellation() {
        var machine = new VendingMachine(new MachineId("VM"), MachineStatus.ACTIVE);
        machine.insertMoney(500);
        var result = new RefundService(machine).cancel("cancelled");
        assertTrue(result.successful());
        assertEquals(500, result.refundedPaise());
        assertEquals(0, machine.getCreditPaise());
    }
}
