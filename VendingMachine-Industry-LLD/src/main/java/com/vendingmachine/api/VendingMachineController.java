package com.vendingmachine.api;

import com.vendingmachine.api.dto.*;
import com.vendingmachine.api.mapper.VendingMachineMapper;
import com.vendingmachine.application.service.*;
import com.vendingmachine.domain.product.ProductId;

public class VendingMachineController {
    private final VendingMachineService machineService;
    private final PurchaseService purchaseService;
    private final RefundService refundService;
    private final VendingMachineMapper mapper = new VendingMachineMapper();
    public VendingMachineController(VendingMachineService machineService, PurchaseService purchaseService, RefundService refundService) {
        this.machineService = machineService; this.purchaseService = purchaseService; this.refundService = refundService;
    }
    public void insertMoney(long amountPaise) { machineService.insertMoney(amountPaise); }
    public void selectProduct(String productId) { machineService.selectProduct(new ProductId(productId)); }
    public PurchaseResponse purchase(PurchaseRequest request) {
        var result = purchaseService.purchase(request.idempotencyKey(), request.paymentMethod());
        return mapper.toResponse(result);
    }
    public com.vendingmachine.application.result.RefundResult cancel(String reason) { return refundService.cancel(reason); }
}
