package com.vendingmachine.payment.strategy;

import com.vendingmachine.payment.gateway.PaymentGateway;

public class CardPaymentStrategy implements PaymentStrategy {
    private final PaymentGateway gateway;
    public CardPaymentStrategy(PaymentGateway gateway) { this.gateway = gateway; }
    public String method() { return "CARD"; }
    public PaymentResult pay(PaymentRequest request) {
        boolean success = gateway.charge(request.reference(), request.amountDuePaise());
        return new PaymentResult(success, 0, request.reference(), success ? "Card payment accepted" : "Card payment failed");
    }
}
