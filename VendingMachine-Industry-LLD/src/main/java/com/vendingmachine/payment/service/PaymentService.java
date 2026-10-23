package com.vendingmachine.payment.service;

import com.vendingmachine.payment.strategy.*;
import com.vendingmachine.domain.exception.PaymentFailedException;
import java.util.*;

public class PaymentService {
    private final Map<String, PaymentStrategy> strategies;
    private final ChangeCalculator changeCalculator;
    public PaymentService(Map<String, PaymentStrategy> strategies, ChangeCalculator changeCalculator) {
        this.strategies = Map.copyOf(strategies); this.changeCalculator = changeCalculator;
    }
    public PaymentResult pay(String method, String reference, long due, long tendered) {
        PaymentStrategy strategy = strategies.get(method.toUpperCase(Locale.ROOT));
        if (strategy == null) throw new IllegalArgumentException("Unsupported payment method: " + method);
        PaymentResult result = strategy.pay(new PaymentRequest(reference, due, tendered));
        if (!result.successful()) throw new PaymentFailedException(result.message());
        return result;
    }
    public long calculateChange(long tendered, long due) { return changeCalculator.calculateChange(tendered, due); }
}
