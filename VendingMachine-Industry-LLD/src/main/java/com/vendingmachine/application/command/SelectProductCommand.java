package com.vendingmachine.application.command;
import com.vendingmachine.domain.product.ProductId;
public record SelectProductCommand(ProductId productId) { public SelectProductCommand { if (productId == null) throw new IllegalArgumentException("productId required"); } }
