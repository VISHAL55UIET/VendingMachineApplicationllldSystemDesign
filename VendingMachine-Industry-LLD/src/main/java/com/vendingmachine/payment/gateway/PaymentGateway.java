package com.vendingmachine.payment.gateway;

public interface PaymentGateway {
    boolean charge(String reference, long amountPaise);
    boolean refund(String reference, long amountPaise);
}
