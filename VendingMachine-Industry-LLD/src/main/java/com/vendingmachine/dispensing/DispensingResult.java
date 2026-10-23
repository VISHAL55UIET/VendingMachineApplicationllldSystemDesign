package com.vendingmachine.dispensing;

public record DispensingResult(boolean successful, String message) {
    public static DispensingResult success() { return new DispensingResult(true, "Product dispensed"); }
    public static DispensingResult failure(String message) { return new DispensingResult(false, message); }
}
