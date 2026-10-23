package com.vendingmachine.payment.strategy;

import com.vendingmachine.payment.gateway.PaymentGateway;
import com.vendingmachine.payment.service.ChangeCalculator;

public class CashPaymentStrategy implements PaymentStrategy {
    private final PaymentGateway gateway;
    private final ChangeCalculator changeCalculator;
    public CashPaymentStrategy(PaymentGateway gateway) { this(gateway, new ChangeCalculator()); }
    public CashPaymentStrategy(PaymentGateway gateway, ChangeCalculator calculator) {
        this.gateway = gateway; this.changeCalculator = calculator;
    }
    public String method() { return "CASH"; }
    public PaymentResult pay(PaymentRequest request) {
        if (request.amountTenderedPaise() < request.amountDuePaise())
            return new PaymentResult(false, 0, request.reference(), "Insufficient cash");
        if (!gateway.charge(request.reference(), request.amountDuePaise()))
            return new PaymentResult(false, 0, request.reference(), "Cash payment failed");
        long change = changeCalculator.calculateChange(request.amountTenderedPaise(), request.amountDuePaise());
        return new PaymentResult(true, change, request.reference(), "Cash payment accepted");
    }
}
