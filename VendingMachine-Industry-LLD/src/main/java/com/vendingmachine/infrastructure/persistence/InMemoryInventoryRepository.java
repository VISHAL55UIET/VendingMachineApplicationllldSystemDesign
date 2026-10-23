package com.vendingmachine.infrastructure.persistence;

import com.vendingmachine.domain.inventory.*;
import com.vendingmachine.domain.product.ProductId;
import com.vendingmachine.repository.InventoryRepository;
import java.util.*;

public class InMemoryInventoryRepository implements InventoryRepository {
    private final Inventory inventory;
    public InMemoryInventoryRepository(Inventory inventory) { this.inventory = Objects.requireNonNull(inventory); }
    public Optional<InventoryItem> findBySlotId(SlotId id) { return inventory.findSlot(id).map(InventoryItem::new); }
    public Optional<InventoryItem> findAvailableByProductId(ProductId id) {
        return inventory.findSlotByProduct(id).map(InventoryItem::new);
    }
    public List<InventoryItem> findAll() { return inventory.allSlots().stream().map(InventoryItem::new).toList(); }
    public void dispenseOne(SlotId id) { inventory.dispenseOne(id); }
    public void restock(SlotId id, int quantity) { inventory.restock(id, quantity); }
}
