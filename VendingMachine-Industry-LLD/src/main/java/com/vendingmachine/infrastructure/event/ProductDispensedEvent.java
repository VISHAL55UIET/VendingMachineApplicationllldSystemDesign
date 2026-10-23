package com.vendingmachine.infrastructure.event;

import java.time.Instant;

public record ProductDispensedEvent(String machineId, String transactionId, String productId, Instant occurredAt) {}
