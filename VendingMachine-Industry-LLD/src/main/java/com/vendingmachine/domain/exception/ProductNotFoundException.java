package com.vendingmachine.domain.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() { super("Product was not found."); }
    public ProductNotFoundException(String message) { super(message); }
}
