package com.vendingmachine.infrastructure.event;

import java.time.Instant;

public record PaymentCompletedEvent(String transactionId, long amountPaise, String method, Instant occurredAt) {}
