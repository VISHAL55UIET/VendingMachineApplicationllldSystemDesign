package com.vendingmachine.domain.machine;

public record MachineId(String value) {
    public MachineId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("MachineId is required");
    }
    @Override public String toString() { return value; }
}
