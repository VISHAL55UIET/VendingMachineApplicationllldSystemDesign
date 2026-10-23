package com.vendingmachine.api.dto;

import com.vendingmachine.domain.transaction.TransactionStatus;

public record PurchaseResponse(String transactionId, String productName, long pricePaise, long changePaise,
                               TransactionStatus status, String message) {}
