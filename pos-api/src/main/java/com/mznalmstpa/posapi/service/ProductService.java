package com.mznalmstpa.posapi.service;

import com.mznalmstpa.posapi.entity.ProductEntity;
import com.mznalmstpa.posapi.model.ProductModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {
//    untuk di implementasikan ke product impl
    List<ProductEntity> getAll();
    Optional<ProductEntity> getById(Long id);
    Optional<ProductEntity> getByCode(String code);
    Optional<ProductEntity> save(ProductModel request);
    Optional<ProductEntity> update(ProductModel request, Long id);
    Optional<ProductEntity> delete(Long id);
}
