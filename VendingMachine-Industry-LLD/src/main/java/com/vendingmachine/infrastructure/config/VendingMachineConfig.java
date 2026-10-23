package com.vendingmachine.infrastructure.config;

import com.vendingmachine.domain.inventory.Inventory;
import com.vendingmachine.domain.machine.*;
import com.vendingmachine.infrastructure.hardware.*;
import com.vendingmachine.infrastructure.persistence.*;
import com.vendingmachine.domain.product.Product;
import com.vendingmachine.domain.inventory.*;
import java.util.List;

public class VendingMachineConfig {
    public VendingMachine createMachine(String id) {
        return new VendingMachine(new MachineId(id), MachineStatus.ACTIVE);
    }
    public Inventory createInventory(List<Slot> slots) {
        Inventory inventory = new Inventory();
        slots.forEach(inventory::addSlot);
        return inventory;
    }
    public MockHardwareController createHardwareController() { return new MockHardwareController(); }
}
