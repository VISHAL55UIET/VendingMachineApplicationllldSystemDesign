package com.vendingmachine.api.dto;

public record InventoryResponse(String slotId, String productId, String productName, int quantity, long pricePaise) {}
