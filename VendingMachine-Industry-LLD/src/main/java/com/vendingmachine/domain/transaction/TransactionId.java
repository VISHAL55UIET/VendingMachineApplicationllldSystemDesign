package com.vendingmachine.domain.transaction;

public record TransactionId(String value) {
    public TransactionId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("TransactionId is required");
    }
    @Override public String toString() { return value; }
}
