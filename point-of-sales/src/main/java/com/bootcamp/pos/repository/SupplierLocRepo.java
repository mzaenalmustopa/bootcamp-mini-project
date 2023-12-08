package com.bootcamp.pos.repository;

import com.bootcamp.pos.model.entity.SupplierLocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierLocRepo extends JpaRepository<SupplierLocEntity, String> {
}
