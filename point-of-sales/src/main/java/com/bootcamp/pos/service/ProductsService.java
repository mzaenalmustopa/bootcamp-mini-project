package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.ProductsRequest;

import java.util.List;
import java.util.Optional;

public interface ProductsService {
    List<ProductsRequest> getAll();
    Optional<ProductsRequest> getById(String id);
    Optional<ProductsRequest> save(ProductsRequest request);
    Optional<ProductsRequest> update(ProductsRequest request, String id);
    Optional<ProductsRequest> delete(String id);
}
