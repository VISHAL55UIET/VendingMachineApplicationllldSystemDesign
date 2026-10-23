package com.vendingmachine.payment.gateway;

public class CardPaymentGateway implements PaymentGateway {
    public boolean charge(String reference, long amountPaise) {
        throw new UnsupportedOperationException("Connect a real card processor before enabling card payments");
    }
    public boolean refund(String reference, long amountPaise) {
        throw new UnsupportedOperationException("Connect a real card processor before enabling card refunds");
    }
}
