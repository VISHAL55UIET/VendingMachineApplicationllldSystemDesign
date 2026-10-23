package com.vendingmachine.admin;

import com.vendingmachine.application.service.InventoryService;
import com.vendingmachine.domain.inventory.SlotId;

public class InventoryRestockService {
    private final InventoryService inventoryService;
    public InventoryRestockService(InventoryService inventoryService) { this.inventoryService = inventoryService; }
    public void restock(SlotId slotId, int quantity) { inventoryService.restock(slotId, quantity); }
}
