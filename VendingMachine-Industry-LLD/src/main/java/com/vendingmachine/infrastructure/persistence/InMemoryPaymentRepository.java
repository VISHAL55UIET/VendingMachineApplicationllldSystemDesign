package com.vendingmachine.infrastructure.persistence;

import com.vendingmachine.domain.payment.*;
import com.vendingmachine.repository.PaymentRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPaymentRepository implements PaymentRepository {
    private final Map<PaymentId, Payment> payments = new ConcurrentHashMap<>();
    public Optional<Payment> findById(PaymentId id) { return Optional.ofNullable(payments.get(id)); }
    public void save(Payment payment) { payments.put(payment.id(), payment); }
}
