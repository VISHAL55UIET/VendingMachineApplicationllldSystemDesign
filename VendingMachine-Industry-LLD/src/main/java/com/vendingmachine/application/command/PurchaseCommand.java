package com.vendingmachine.application.command;
public record PurchaseCommand(String idempotencyKey, String paymentMethod) { public PurchaseCommand { if (idempotencyKey == null || idempotencyKey.isBlank() || paymentMethod == null || paymentMethod.isBlank()) throw new IllegalArgumentException("idempotencyKey and paymentMethod required"); } }
