package com.vendingmachine.payment.service;

import com.vendingmachine.payment.gateway.PaymentGateway;

public class RefundProcessor {
    private final PaymentGateway gateway;
    public RefundProcessor(PaymentGateway gateway) { this.gateway = gateway; }
    public boolean refund(String reference, long amountPaise) {
        if (amountPaise <= 0) return amountPaise == 0;
        return gateway.refund(reference, amountPaise);
    }
}
