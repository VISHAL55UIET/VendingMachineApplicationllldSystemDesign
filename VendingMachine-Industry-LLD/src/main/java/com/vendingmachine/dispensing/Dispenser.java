package com.vendingmachine.dispensing;

import com.vendingmachine.domain.inventory.SlotId;

public interface Dispenser {
    DispensingResult dispense(SlotId slotId);
}
