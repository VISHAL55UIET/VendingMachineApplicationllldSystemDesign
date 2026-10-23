
        package com.vendingmachine.admin;

import com.vendingmachine.domain.inventory.InventoryItem;
import com.vendingmachine.domain.machine.VendingMachine;
import com.vendingmachine.repository.InventoryRepository;

public class MachineMonitoringService {

    private final VendingMachine machine;
    private final InventoryRepository inventory;

    public MachineMonitoringService(
            VendingMachine machine,
            InventoryRepository inventory
    ) {
        this.machine = machine;
        this.inventory = inventory;
    }

    public String healthSummary() {

        long emptySlots = inventory.findAll()
                .stream()
                .filter(item -> item.getQuantity() == 0)
                .count();

        return "machine=" + machine.getId()
                + ", status=" + machine.getStatus()
                + ", emptySlots=" + emptySlots;
    }
}