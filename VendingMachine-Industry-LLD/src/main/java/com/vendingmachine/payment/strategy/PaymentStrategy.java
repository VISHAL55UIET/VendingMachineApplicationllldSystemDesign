package com.vendingmachine.payment.strategy;

public interface PaymentStrategy {
    String method();
    PaymentResult pay(PaymentRequest request);
}
