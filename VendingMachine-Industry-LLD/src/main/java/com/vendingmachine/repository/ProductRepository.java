package com.vendingmachine.repository;

import com.vendingmachine.domain.product.Product;
import com.vendingmachine.domain.product.ProductId;
import java.util.*;

public interface ProductRepository {
    Optional<Product> findById(ProductId id);
    List<Product> findAll();
}
