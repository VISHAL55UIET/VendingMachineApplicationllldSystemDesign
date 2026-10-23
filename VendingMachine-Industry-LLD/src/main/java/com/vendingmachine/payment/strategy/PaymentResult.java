package com.vendingmachine.payment.strategy;

public record PaymentResult(boolean successful, long changePaise, String reference, String message) {}
