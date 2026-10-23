package com.vendingmachine.domain.inventory;

public record InventoryItem(Slot slot) {
    public InventoryItem {
        if (slot == null) throw new IllegalArgumentException("Slot is required");
    }
    public int getQuantity() { return slot.getQuantity(); }
    public SlotId getSlotId() { return slot.getId(); }
}
