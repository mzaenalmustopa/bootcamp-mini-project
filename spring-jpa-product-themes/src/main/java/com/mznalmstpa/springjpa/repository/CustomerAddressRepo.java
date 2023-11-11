package com.mznalmstpa.springjpa.repository;

import com.mznalmstpa.springjpa.entity.CustomerAddressEntity;
import com.mznalmstpa.springjpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepo extends JpaRepository<CustomerAddressEntity, Long> {
    boolean existsByCustomer(CustomerEntity customerAddressModel);
}
