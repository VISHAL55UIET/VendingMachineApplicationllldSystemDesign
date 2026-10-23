package com.vendingmachine.api.mapper;

import com.vendingmachine.api.dto.*;
import com.vendingmachine.application.result.PurchaseResult;

public class VendingMachineMapper {
    public PurchaseResponse toResponse(PurchaseResult result) {
        return new PurchaseResponse(result.transactionId().value(), result.productName(), result.pricePaise(),
                result.changePaise(), result.status(), result.message());
    }
}
