package com.mznalmstpa.posapi.repository;

import com.mznalmstpa.posapi.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<SupplierEntity, Long> {
}
