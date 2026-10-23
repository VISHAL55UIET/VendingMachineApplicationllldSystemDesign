package com.vendingmachine.domain.inventory;

import com.vendingmachine.domain.product.Product;
import com.vendingmachine.domain.exception.OutOfStockException;

public class Slot {
    private final SlotId id;
    private Product product;
    private int quantity;

    public Slot(SlotId id, Product product, int quantity) {
        if (id == null || product == null || quantity < 0) throw new IllegalArgumentException("Invalid slot");
        this.id = id; this.product = product; this.quantity = quantity;
    }
    public synchronized void dispenseOne() {
        if (quantity <= 0) throw new OutOfStockException();
        quantity--;
    }
    public synchronized void restock(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Restock amount must be positive");
        quantity = Math.addExact(quantity, amount);
    }
    public SlotId getId() { return id; }
    public Product getProduct() { return product; }
    public synchronized int getQuantity() { return quantity; }
    public synchronized boolean isEmpty() { return quantity == 0; }
}
