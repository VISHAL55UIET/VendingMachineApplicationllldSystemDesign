package com.vendingmachine.application.result;

import com.vendingmachine.domain.transaction.TransactionId;
import com.vendingmachine.domain.transaction.TransactionStatus;

public record PurchaseResult(TransactionId transactionId, String productName, long pricePaise,
                             long changePaise, TransactionStatus status, String message) {}
