package com.mznalmstpa.springjpa.repository;

import com.mznalmstpa.springjpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
    Optional<CategoryEntity> findByName(String name);
}
