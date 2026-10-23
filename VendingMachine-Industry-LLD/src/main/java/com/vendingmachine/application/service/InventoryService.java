package com.vendingmachine.application.service;

import com.vendingmachine.domain.inventory.InventoryItem;
import com.vendingmachine.domain.inventory.SlotId;
import com.vendingmachine.domain.product.ProductId;
import com.vendingmachine.repository.InventoryRepository;
import java.util.List;
import java.util.Optional;

public class InventoryService {
    private final InventoryRepository repository;
    public InventoryService(InventoryRepository repository) { this.repository = repository; }
    public Optional<InventoryItem> findAvailable(ProductId id) { return repository.findAvailableByProductId(id); }
    public List<InventoryItem> allItems() { return repository.findAll(); }
    public void restock(SlotId id, int quantity) { repository.restock(id, quantity); }
}
