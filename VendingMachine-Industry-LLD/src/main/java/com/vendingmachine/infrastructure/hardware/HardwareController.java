package com.vendingmachine.infrastructure.hardware;

import com.vendingmachine.domain.inventory.SlotId;

public interface HardwareController {
    boolean releaseProduct(SlotId slotId);
}
