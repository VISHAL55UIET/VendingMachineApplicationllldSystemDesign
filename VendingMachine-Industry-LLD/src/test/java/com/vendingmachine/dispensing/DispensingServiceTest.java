package com.vendingmachine.dispensing;

import com.vendingmachine.domain.inventory.SlotId;
import com.vendingmachine.infrastructure.hardware.MockHardwareController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DispensingServiceTest {
    @Test void succeedsWhenHardwareWorks() {
        var controller = new MockHardwareController();
        var service = new DispensingService(new HardwareDispenser(controller));
        assertTrue(service.dispense(new SlotId("A1")).successful());
    }
    @Test void reportsHardwareFailure() {
        var controller = new MockHardwareController();
        controller.setShouldFail(true);
        var service = new DispensingService(new HardwareDispenser(controller));
        assertFalse(service.dispense(new SlotId("A1")).successful());
    }
}
