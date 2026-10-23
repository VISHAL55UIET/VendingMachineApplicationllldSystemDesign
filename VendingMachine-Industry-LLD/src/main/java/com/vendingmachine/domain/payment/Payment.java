package com.vendingmachine.domain.payment;

import java.time.Instant;

public record Payment(PaymentId id, long amountPaise, String method, PaymentStatus status, Instant createdAt) {
    public Payment {
        if (id == null || amountPaise < 0 || method == null || status == null || createdAt == null)
            throw new IllegalArgumentException("Invalid payment");
    }
    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(id, amountPaise, method, newStatus, createdAt);
    }
}
