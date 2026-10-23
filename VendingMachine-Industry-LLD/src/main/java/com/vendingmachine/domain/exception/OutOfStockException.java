package com.vendingmachine.domain.exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException() { super("Requested product is out of stock."); }
    public OutOfStockException(String message) { super(message); }
}
