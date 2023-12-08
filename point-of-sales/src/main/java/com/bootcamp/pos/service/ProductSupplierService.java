package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.ProductSupplierRequest;

import java.util.List;
import java.util.Optional;

public interface ProductSupplierService {
    List<ProductSupplierRequest> getAll();
    Optional<ProductSupplierRequest> getById(String id);
    Optional<ProductSupplierRequest> save (ProductSupplierRequest request);
    Optional<ProductSupplierRequest> update (ProductSupplierRequest request, String id);
    Optional<ProductSupplierRequest> delete (String id);
}
