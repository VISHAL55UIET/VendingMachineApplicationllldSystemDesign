package com.vendingmachine.repository;

import com.vendingmachine.domain.inventory.InventoryItem;
import com.vendingmachine.domain.inventory.SlotId;
import com.vendingmachine.domain.product.ProductId;
import java.util.*;

public interface InventoryRepository {
    Optional<InventoryItem> findBySlotId(SlotId id);
    Optional<InventoryItem> findAvailableByProductId(ProductId id);
    List<InventoryItem> findAll();
    void dispenseOne(SlotId id);
    void restock(SlotId id, int quantity);
}
