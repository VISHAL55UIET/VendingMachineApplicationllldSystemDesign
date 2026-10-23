package com.vendingmachine.state;

import com.vendingmachine.domain.machine.VendingMachine;
import com.vendingmachine.domain.product.ProductId;

public class ProductSelectedState implements VendingMachineState {
    public String name() { return "PRODUCT_SELECTED"; }
    @Override public VendingMachineState afterMoneyInserted(VendingMachine machine) { return this; }
    @Override public VendingMachineState afterProductSelected(VendingMachine machine) { return this; }
    @Override public void validatePurchase(VendingMachine machine, ProductId id, long price) {
        if (machine.getSelectedProductId() == null || !machine.getSelectedProductId().equals(id))
            throw new IllegalStateException("Selected product does not match purchase request");
        machine.requireSufficientCredit(price);
    }
}
