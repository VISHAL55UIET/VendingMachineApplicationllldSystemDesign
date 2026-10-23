package com.vendingmachine.domain.exception;

public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException() { super("Payment could not be completed."); }
    public PaymentFailedException(String message) { super(message); }
}
