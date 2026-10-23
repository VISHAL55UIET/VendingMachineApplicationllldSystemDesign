package com.vendingmachine.domain.product;

import java.util.Objects;

public record ProductId(String value) {
    public ProductId {
        if (value == null || value.isBlank()) throw new IllegalArgumentException("ProductId is required");
    }
    @Override public String toString() { return value; }
}
