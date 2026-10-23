package com.vendingmachine.api.dto;

public record PurchaseRequest(String idempotencyKey, String paymentMethod) {}
