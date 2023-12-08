package com.bootcamp.pos.service;

import com.bootcamp.pos.model.request.CustomerAddressRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerAddressService {
    List<CustomerAddressRequest> getAll();
    Optional<CustomerAddressRequest> getById(String id);
    Optional<CustomerAddressRequest> save (CustomerAddressRequest request);
    Optional<CustomerAddressRequest> update (CustomerAddressRequest request, String id);
    Optional<CustomerAddressRequest> delete (String id);
}
