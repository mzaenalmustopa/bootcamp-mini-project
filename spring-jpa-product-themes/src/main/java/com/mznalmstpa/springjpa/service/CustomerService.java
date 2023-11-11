package com.mznalmstpa.springjpa.service;

import com.mznalmstpa.springjpa.model.CustomerAddressModel;
import com.mznalmstpa.springjpa.model.CustomerModel;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerModel> gets();
    Optional<CustomerModel> getById(Long id);
    void save (CustomerModel request);
    void saveAddress(CustomerAddressModel request);
    void update (CustomerModel request, Long id);
    void delete(Long id);
}
