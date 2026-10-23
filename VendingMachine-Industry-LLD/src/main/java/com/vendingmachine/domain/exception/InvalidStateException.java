package com.vendingmachine.domain.exception;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException() { super("Operation is not valid in the current state."); }
    public InvalidStateException(String message) { super(message); }
}
