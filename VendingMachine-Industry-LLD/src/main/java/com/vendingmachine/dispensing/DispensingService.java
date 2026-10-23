package com.vendingmachine.dispensing;

import com.vendingmachine.domain.inventory.SlotId;

public class DispensingService {
    private final Dispenser dispenser;
    public DispensingService(Dispenser dispenser) { this.dispenser = dispenser; }
    public DispensingResult dispense(SlotId slotId) { return dispenser.dispense(slotId); }
}
