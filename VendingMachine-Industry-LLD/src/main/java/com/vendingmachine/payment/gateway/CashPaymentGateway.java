package com.vendingmachine.payment.gateway;

public class CashPaymentGateway implements PaymentGateway {
    public boolean charge(String reference, long amountPaise) { return amountPaise >= 0; }
    public boolean refund(String reference, long amountPaise) { return amountPaise >= 0; }
}
