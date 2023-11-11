package com.mznalmstpa.springjpa.service;

import com.mznalmstpa.springjpa.model.CustomerAddressModel;

import java.util.List;
import java.util.Optional;

public interface CustomerAddressService {
    List<CustomerAddressModel> gets();
    Optional<CustomerAddressModel> getById(Long id);
    void save (CustomerAddressModel request);
    void update(CustomerAddressModel request, Long id);
    void delete(Long id);
}
