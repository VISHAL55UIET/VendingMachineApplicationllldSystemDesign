package com.vendingmachine.application.result;

import com.vendingmachine.domain.transaction.TransactionId;
import com.vendingmachine.domain.transaction.TransactionStatus;

public record TransactionResult(TransactionId transactionId, TransactionStatus status, String message) {}
