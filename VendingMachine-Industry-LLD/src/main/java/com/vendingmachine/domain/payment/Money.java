package com.vendingmachine.domain.payment;

public record Money(long paise) {
    public Money {
        if (paise < 0) throw new IllegalArgumentException("Money cannot be negative");
    }
    public Money plus(Money other) { return new Money(Math.addExact(paise, other.paise)); }
    public Money minus(Money other) {
        if (other.paise > paise) throw new IllegalArgumentException("Money result cannot be negative");
        return new Money(paise - other.paise);
    }
    public boolean covers(Money other) { return paise >= other.paise; }
    public static Money ofPaise(long paise) { return new Money(paise); }
}
