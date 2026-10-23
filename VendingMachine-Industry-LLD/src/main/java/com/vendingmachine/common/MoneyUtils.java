package com.vendingmachine.common;

public final class MoneyUtils {
    private MoneyUtils() {}
    public static String formatPaise(long paise) {
        return "₹" + (paise / 100) + "." + String.format("%02d", Math.abs(paise % 100));
    }
}
