package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.SupplierLocRequest;

import java.util.List;
import java.util.Optional;

public interface SupplierLocService {
    List<SupplierLocRequest> getAll();
    Optional<SupplierLocRequest> getById(String id);
    Optional<SupplierLocRequest> save (SupplierLocRequest request);
    Optional<SupplierLocRequest> update (SupplierLocRequest request, String id);
    Optional<SupplierLocRequest> delete (String id);
}
