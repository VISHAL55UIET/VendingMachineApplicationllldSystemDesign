package com.vendingmachine.state;

import com.vendingmachine.domain.machine.VendingMachine;
import com.vendingmachine.domain.product.ProductId;

public interface VendingMachineState {
    String name();
    default VendingMachineState afterMoneyInserted(VendingMachine machine) { throw invalid("insert money"); }
    default VendingMachineState afterProductSelected(VendingMachine machine) { throw invalid("select product"); }
    default void validatePurchase(VendingMachine machine, ProductId productId, long pricePaise) {
        throw invalid("purchase");
    }
    default VendingMachineState afterCancel(VendingMachine machine) { return new IdleState(); }
    private IllegalStateException invalid(String operation) {
        return new IllegalStateException("Cannot " + operation + " while machine is in " + name());
    }
}
