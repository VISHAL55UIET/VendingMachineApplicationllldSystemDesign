package com.vendingmachine.application.service;

import com.vendingmachine.application.result.RefundResult;
import com.vendingmachine.domain.machine.VendingMachine;

public class RefundService {
    private final VendingMachine machine;
    public RefundService(VendingMachine machine) { this.machine = machine; }
    public synchronized RefundResult cancel(String reason) {
        long amount = machine.refundAllCredit();
        return new RefundResult(amount, reason == null ? "Customer cancelled" : reason, true);
    }
}
