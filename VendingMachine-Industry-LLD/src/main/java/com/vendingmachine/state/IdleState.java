package com.vendingmachine.state;

import com.vendingmachine.domain.machine.VendingMachine;
import com.vendingmachine.domain.product.ProductId;

public class IdleState implements VendingMachineState {
    public String name() { return "IDLE"; }
    @Override public VendingMachineState afterMoneyInserted(VendingMachine machine) { return new HasCreditState(); }
    @Override public VendingMachineState afterProductSelected(VendingMachine machine) { return new ProductSelectedState(); }
    @Override public void validatePurchase(VendingMachine machine, ProductId id, long price) {
        throw new IllegalStateException("Insert money and select a product before purchasing");
    }
}
