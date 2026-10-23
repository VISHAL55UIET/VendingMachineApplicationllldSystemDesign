package com.vendingmachine.state;

import com.vendingmachine.domain.machine.VendingMachine;
import com.vendingmachine.domain.product.ProductId;

public class HasCreditState implements VendingMachineState {
    public String name() { return "HAS_CREDIT"; }
    @Override public VendingMachineState afterMoneyInserted(VendingMachine machine) { return this; }
    @Override public VendingMachineState afterProductSelected(VendingMachine machine) { return new ProductSelectedState(); }
    @Override public void validatePurchase(VendingMachine machine, ProductId id, long price) {
        throw new IllegalStateException("Select a product before purchasing");
    }
}
