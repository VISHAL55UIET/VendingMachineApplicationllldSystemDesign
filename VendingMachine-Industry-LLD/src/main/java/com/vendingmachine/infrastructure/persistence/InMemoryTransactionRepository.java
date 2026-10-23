package com.vendingmachine.infrastructure.persistence;

import com.vendingmachine.domain.transaction.VendingTransaction;
import com.vendingmachine.repository.TransactionRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final Map<String, VendingTransaction> byKey = new ConcurrentHashMap<>();
    public Optional<VendingTransaction> findByIdempotencyKey(String key) { return Optional.ofNullable(byKey.get(key)); }
    public void save(VendingTransaction tx) { byKey.put(tx.getIdempotencyKey(), tx); }
}
