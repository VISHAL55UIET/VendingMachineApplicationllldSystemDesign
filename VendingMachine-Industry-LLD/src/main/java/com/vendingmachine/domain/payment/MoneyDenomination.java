package com.vendingmachine.domain.payment;

public enum MoneyDenomination {
    ONE_RUPEE(100), TWO_RUPEES(200), FIVE_RUPEES(500), TEN_RUPEES(1000), TWENTY_RUPEES(2000);
    private final long paise;
    MoneyDenomination(long paise) { this.paise = paise; }
    public long paise() { return paise; }
}
