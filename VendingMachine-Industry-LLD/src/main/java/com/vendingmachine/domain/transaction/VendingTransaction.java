package com.vendingmachine.domain.transaction;

import java.time.Instant;

public class VendingTransaction {
    private final TransactionId id;
    private final String idempotencyKey;
    private final String productId;
    private final long pricePaise;
    private final long insertedPaise;
    private final Instant createdAt;
    private TransactionStatus status;

    public VendingTransaction(TransactionId id, String idempotencyKey, String productId,
                              long pricePaise, long insertedPaise, Instant createdAt) {
        if (id == null || idempotencyKey == null || idempotencyKey.isBlank() || productId == null || createdAt == null)
            throw new IllegalArgumentException("Invalid transaction");
        if (pricePaise <= 0 || insertedPaise < 0) throw new IllegalArgumentException("Invalid amount");
        this.id = id; this.idempotencyKey = idempotencyKey; this.productId = productId;
        this.pricePaise = pricePaise; this.insertedPaise = insertedPaise; this.createdAt = createdAt;
        this.status = TransactionStatus.CREATED;
    }
    public synchronized void transitionTo(TransactionStatus next) {
        if (status == TransactionStatus.COMPLETED || status == TransactionStatus.REFUNDED || status == TransactionStatus.CANCELLED)
            throw new IllegalStateException("Terminal transaction cannot transition from " + status);
        status = next;
    }
    public TransactionId getId() { return id; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public String getProductId() { return productId; }
    public long getPricePaise() { return pricePaise; }
    public long getInsertedPaise() { return insertedPaise; }
    public Instant getCreatedAt() { return createdAt; }
    public synchronized TransactionStatus getStatus() { return status; }
}
