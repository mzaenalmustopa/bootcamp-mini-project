package com.bootcamp.pos.service;


import com.bootcamp.pos.model.request.ProductTypeRequest;

import java.util.List;
import java.util.Optional;

public interface ProductTypeService {
    List<ProductTypeRequest> getAll();
    Optional<ProductTypeRequest> getById(String id);
    Optional<ProductTypeRequest> save (ProductTypeRequest request);
    Optional<ProductTypeRequest> update (ProductTypeRequest request, String id);
    Optional<ProductTypeRequest> delete (String id);
}
