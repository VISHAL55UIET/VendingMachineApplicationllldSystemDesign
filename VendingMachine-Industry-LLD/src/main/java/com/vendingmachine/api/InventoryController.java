package com.vendingmachine.api;

import com.vendingmachine.api.dto.InventoryResponse;
import com.vendingmachine.application.service.InventoryService;
import java.util.List;

public class InventoryController {
    private final InventoryService service;
    public InventoryController(InventoryService service) { this.service = service; }
    public List<InventoryResponse> list() {
        return service.allItems().stream().map(item -> new InventoryResponse(item.getSlotId().value(),
                item.slot().getProduct().id().value(), item.slot().getProduct().name(),
                item.getQuantity(), item.slot().getProduct().pricePaise())).toList();
    }
}
