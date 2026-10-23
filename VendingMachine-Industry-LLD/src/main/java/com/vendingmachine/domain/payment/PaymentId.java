package com.vendingmachine.domain.payment;

public record PaymentId(String value) {
    public PaymentId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("PaymentId is required");
    }
}
