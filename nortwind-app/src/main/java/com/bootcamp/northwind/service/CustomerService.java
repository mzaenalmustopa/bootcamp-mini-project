package com.bootcamp.northwind.service;

import com.bootcamp.northwind.model.request.CustomerRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerRequest> getAll();
    Optional<CustomerRequest> getById(Long id);
    Optional<CustomerRequest> save (CustomerRequest request);
    Optional<CustomerRequest> update(CustomerRequest request, Long id);
    Optional<CustomerRequest> delete(Long id);
}
