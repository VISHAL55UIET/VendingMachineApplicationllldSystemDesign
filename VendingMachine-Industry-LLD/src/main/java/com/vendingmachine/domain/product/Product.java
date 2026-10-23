package com.vendingmachine.domain.product;

public record Product(ProductId id, String name, long pricePaise, ProductCategory category) {
    public Product {
        if (id == null || name == null || name.isBlank() || category == null) throw new IllegalArgumentException("Invalid product");
        if (pricePaise <= 0) throw new IllegalArgumentException("Price must be positive");
    }
}
