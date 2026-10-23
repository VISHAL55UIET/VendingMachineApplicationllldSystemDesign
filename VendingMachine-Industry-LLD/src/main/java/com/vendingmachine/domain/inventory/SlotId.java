package com.vendingmachine.domain.inventory;

public record SlotId(String value) {
    public SlotId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("SlotId is required");
    }
    @Override public String toString() { return value; }
}
