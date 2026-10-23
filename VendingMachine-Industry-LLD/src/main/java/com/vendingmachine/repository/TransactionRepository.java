package com.vendingmachine.repository;

import com.vendingmachine.domain.transaction.VendingTransaction;
import java.util.Optional;

public interface TransactionRepository {
    Optional<VendingTransaction> findByIdempotencyKey(String key);
    void save(VendingTransaction transaction);
}
