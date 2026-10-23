package com.vendingmachine.domain.inventory;

import com.vendingmachine.domain.product.ProductId;
import com.vendingmachine.domain.exception.OutOfStockException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private final Map<SlotId, Slot> slots = new ConcurrentHashMap<>();

    public void addSlot(Slot slot) {
        if (slots.putIfAbsent(slot.getId(), slot) != null) throw new IllegalArgumentException("Duplicate slot " + slot.getId());
    }
    public Optional<Slot> findSlot(SlotId id) { return Optional.ofNullable(slots.get(id)); }
    public Optional<Slot> findSlotByProduct(ProductId productId) {
        return slots.values().stream().filter(s -> s.getProduct().id().equals(productId) && !s.isEmpty()).findFirst();
    }
    public List<Slot> allSlots() { return List.copyOf(slots.values()); }
    public void dispenseOne(SlotId id) {
        Slot slot = slots.get(id);
        if (slot == null) throw new IllegalArgumentException("Unknown slot " + id);
        slot.dispenseOne();
    }
    public void restock(SlotId id, int quantity) {
        Slot slot = slots.get(id);
        if (slot == null) throw new IllegalArgumentException("Unknown slot " + id);
        slot.restock(quantity);
    }
    public boolean hasStock(ProductId id) {
        return slots.values().stream().anyMatch(s -> s.getProduct().id().equals(id) && !s.isEmpty());
    }
}
