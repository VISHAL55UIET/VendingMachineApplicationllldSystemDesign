package com.vendingmachine.infrastructure.hardware;

import com.vendingmachine.domain.inventory.SlotId;

public class MockHardwareController implements HardwareController {
    private volatile boolean shouldFail;
    public boolean releaseProduct(SlotId slotId) { return !shouldFail; }
    public void setShouldFail(boolean shouldFail) { this.shouldFail = shouldFail; }
}
