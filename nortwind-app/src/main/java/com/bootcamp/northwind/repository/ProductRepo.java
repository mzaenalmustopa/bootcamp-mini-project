package com.bootcamp.northwind.repository;

import com.bootcamp.northwind.model.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductsEntity, Long> {
}
