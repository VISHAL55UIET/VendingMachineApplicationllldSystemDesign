package com.vendingmachine.payment.strategy;

public record PaymentRequest(String reference, long amountDuePaise, long amountTenderedPaise) {
    public PaymentRequest {
        if (reference == null || reference.isBlank() || amountDuePaise < 0 || amountTenderedPaise < 0)
            throw new IllegalArgumentException("Invalid payment request");
    }
}
