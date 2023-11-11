package com.mznalmstpa.springjpa.service;


import com.mznalmstpa.springjpa.model.CategoryModel;

import java.util.List;
import java.util.Optional;

public interface CategoryService{

    List<CategoryModel> gets();
    Optional<CategoryModel> getById(Long id);
    void save (CategoryModel request);
    void update(CategoryModel request, Long id);
    void delete(Long id);
}
