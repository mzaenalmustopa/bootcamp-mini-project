package com.bootcamp.northwind.service;

import com.bootcamp.northwind.model.request.SupplierRequest;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<SupplierRequest> getAll();
    Optional<SupplierRequest> getById(Long id);
    Optional<SupplierRequest> save(SupplierRequest request);
    Optional<SupplierRequest> update (SupplierRequest request, Long id );
    Optional<SupplierRequest> delete (Long id);
}
