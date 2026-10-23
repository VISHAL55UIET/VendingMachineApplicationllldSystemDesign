package com.vendingmachine.domain.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() { super("Inserted amount is insufficient."); }
    public InsufficientFundsException(String message) { super(message); }
}
