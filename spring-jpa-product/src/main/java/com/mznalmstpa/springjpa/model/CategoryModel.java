package com.mznalmstpa.springjpa.model;

import com.mznalmstpa.springjpa.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel {
    private Long id;
    private String name;

    // dependency injection
    public CategoryModel(CategoryEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
