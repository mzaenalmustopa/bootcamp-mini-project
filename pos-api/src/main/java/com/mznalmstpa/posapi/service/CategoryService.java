package com.mznalmstpa.posapi.service;

import com.mznalmstpa.posapi.entity.CategoryEntity;
import com.mznalmstpa.posapi.model.CategoryModel;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
     // untuk di implementasikan ke impl
    List<CategoryEntity> getAll();
    Optional<CategoryEntity> getById(Long id);
    Optional<CategoryEntity> getByCode(String code);
    Optional<CategoryEntity> save(CategoryModel request);
    Optional<CategoryEntity> update(CategoryModel request, Long id);
    Optional<CategoryEntity> delete(Long id);
}
