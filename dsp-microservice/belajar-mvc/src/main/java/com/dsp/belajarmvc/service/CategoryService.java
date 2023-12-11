package com.dsp.belajarmvc.service;

import com.dsp.belajarmvc.model.entity.CategoryEntity;
import com.dsp.belajarmvc.model.request.CategoryRequest;
import com.dsp.belajarmvc.model.response.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryResponse> getAll();
    Optional<CategoryResponse> getById(Long id);
    Optional<CategoryResponse> save(CategoryRequest request);
    Optional<CategoryResponse> update(CategoryRequest request, Long id);
    Optional<CategoryResponse> delete(Long id);
}
