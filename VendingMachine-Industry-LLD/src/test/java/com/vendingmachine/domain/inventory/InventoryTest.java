package com.vendingmachine.domain.inventory;

import com.vendingmachine.domain.product.*;
import com.vendingmachine.domain.exception.OutOfStockException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    @Test void decrementsStockOnDispense() {
        Inventory inventory = new Inventory();
        Product p = new Product(new ProductId("P1"), "Water", 100, ProductCategory.DRINK);
        SlotId id = new SlotId("A1");
        inventory.addSlot(new Slot(id, p, 1));
        inventory.dispenseOne(id);
        assertTrue(inventory.findSlot(id).orElseThrow().isEmpty());
        assertThrows(OutOfStockException.class, () -> inventory.dispenseOne(id));
    }
}
