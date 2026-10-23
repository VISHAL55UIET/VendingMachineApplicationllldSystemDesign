package com.vendingmachine.application.service;

import com.vendingmachine.domain.machine.VendingMachine;
import com.vendingmachine.domain.product.ProductId;
import com.vendingmachine.domain.exception.ProductNotFoundException;
import com.vendingmachine.repository.InventoryRepository;
import com.vendingmachine.repository.ProductRepository;

public class VendingMachineService {
    private final VendingMachine machine;
    private final InventoryRepository inventory;
    private final ProductRepository products;
    public VendingMachineService(VendingMachine machine, InventoryRepository inventory, ProductRepository products) {
        this.machine = machine; this.inventory = inventory; this.products = products;
    }
    public void insertMoney(long paise) { machine.insertMoney(paise); }
    public void selectProduct(ProductId id) {
        products.findById(id).orElseThrow(ProductNotFoundException::new);
        if (inventory.findAvailableByProductId(id).isEmpty())
            throw new com.vendingmachine.domain.exception.OutOfStockException();
        machine.selectProduct(id);
    }
    public VendingMachine getMachine() { return machine; }
}
