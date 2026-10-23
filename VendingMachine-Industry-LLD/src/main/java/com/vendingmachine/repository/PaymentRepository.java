package com.vendingmachine.repository;

import com.vendingmachine.domain.payment.Payment;
import com.vendingmachine.domain.payment.PaymentId;
import java.util.Optional;

public interface PaymentRepository {
    Optional<Payment> findById(PaymentId id);
    void save(Payment payment);
}
