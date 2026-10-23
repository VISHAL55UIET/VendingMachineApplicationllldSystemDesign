package com.vendingmachine.domain.exception;

public class MachineUnavailableException extends RuntimeException {
    public MachineUnavailableException() { super("Machine is unavailable."); }
    public MachineUnavailableException(String message) { super(message); }
}
