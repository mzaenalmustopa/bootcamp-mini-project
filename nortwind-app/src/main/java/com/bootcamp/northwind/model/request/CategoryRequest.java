package com.bootcamp.northwind.model.request;

import com.bootcamp.northwind.model.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private Long id;
    private String categoryName;
    private String description;
    private List<ProductsRequest> products = new ArrayList<>();

    public CategoryRequest(CategoryEntity entity) {
        BeanUtils.copyProperties(entity, this);

        if (!entity.getProducts().isEmpty()){
            this.products = entity.getProducts().stream().map(ProductsRequest::new).collect(Collectors.toList());
        }
    }
}
