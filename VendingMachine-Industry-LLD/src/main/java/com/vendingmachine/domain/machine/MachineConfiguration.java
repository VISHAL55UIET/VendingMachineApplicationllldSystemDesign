package com.vendingmachine.domain.machine;

public record MachineConfiguration(long maximumCreditPaise, int maximumItemsPerSlot) {
    public MachineConfiguration {
        if (maximumCreditPaise <= 0 || maximumItemsPerSlot <= 0) throw new IllegalArgumentException("Invalid machine configuration");
    }
    public static MachineConfiguration defaults() { return new MachineConfiguration(100_000, 100); }
}
