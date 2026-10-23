
        package com.vendingmachine.dispensing;

import com.vendingmachine.dispensing.DispensingResult;
import com.vendingmachine.domain.inventory.SlotId;
import com.vendingmachine.infrastructure.hardware.HardwareController;

public class HardwareDispenser implements Dispenser {

    private final HardwareController controller;

    public HardwareDispenser(HardwareController controller) {
        if (controller == null) {
            throw new IllegalArgumentException(
                    "HardwareController cannot be null"
            );
        }
        this.controller = controller;
    }

    @Override
    public DispensingResult dispense(SlotId slotId) {

        if (slotId == null) {
            return DispensingResult.failure(
                    "Slot ID cannot be null"
            );
        }

        boolean released = controller.releaseProduct(slotId);

        if (released) {
            return DispensingResult.success();
        }

        return DispensingResult.failure(
                "Hardware did not release product from slot: " + slotId
        );
    }
}
