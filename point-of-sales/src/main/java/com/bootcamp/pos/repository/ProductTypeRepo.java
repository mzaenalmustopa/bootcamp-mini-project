package com.bootcamp.pos.repository;

import com.bootcamp.pos.model.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProductTypeEntity, String> {
}
