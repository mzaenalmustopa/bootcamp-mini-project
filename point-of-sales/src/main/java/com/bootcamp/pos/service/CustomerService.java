package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.CustomerRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerRequest> getAll();
    Optional<CustomerRequest> getById(String id);
    Optional<CustomerRequest> save(CustomerRequest request);
    Optional<CustomerRequest> update(CustomerRequest request, String id);
    Optional<CustomerRequest> delete(String id);
}
