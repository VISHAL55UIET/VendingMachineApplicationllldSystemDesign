package com.vendingmachine.infrastructure.persistence;

import com.vendingmachine.domain.product.*;
import com.vendingmachine.repository.ProductRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<ProductId, Product> products = new ConcurrentHashMap<>();
    public InMemoryProductRepository(Collection<Product> initial) { initial.forEach(p -> products.put(p.id(), p)); }
    public Optional<Product> findById(ProductId id) { return Optional.ofNullable(products.get(id)); }
    public List<Product> findAll() { return List.copyOf(products.values()); }
}
