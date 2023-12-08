package com.bootcamp.pos.repository;

import com.bootcamp.pos.model.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductsEntity, String > {
}
