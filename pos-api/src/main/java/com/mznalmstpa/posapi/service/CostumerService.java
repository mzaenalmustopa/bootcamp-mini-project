package com.mznalmstpa.posapi.service;

import com.mznalmstpa.posapi.entity.CustomerEntity;
import com.mznalmstpa.posapi.model.CustomerModel;

import java.util.List;
import java.util.Optional;

public interface CostumerService {
    List<CustomerEntity> getAll();
    Optional<CustomerEntity> getById(Long id);
    Optional<CustomerEntity> save(CustomerModel request);
    Optional<CustomerEntity> update(CustomerModel request, Long id);
    Optional<CustomerEntity> delete(Long id);
}
