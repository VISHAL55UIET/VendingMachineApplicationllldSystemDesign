package com.vendingmachine.domain.exception;

public class DispensingFailedException extends RuntimeException {
    public DispensingFailedException() { super("Product dispensing failed."); }
    public DispensingFailedException(String message) { super(message); }
}
