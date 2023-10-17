package com.mznalmstpa.posapi.repository;

import com.mznalmstpa.posapi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity,Long>{
    // list dari propertu category entity
    Optional<CategoryEntity> findByCode(String code);
}
