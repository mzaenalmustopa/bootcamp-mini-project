package com.bootcamp.northwind.model.request;

import com.bootcamp.northwind.model.entity.ProductsEntity;
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
public class ProductsRequest {
    private Long id;
    private String productName;
    private Long supplierId;
    private Long categoryId;
    private String supplierName;
    private String categoryName;
    private Double quantity;
    private Double price;
    private Double stock;
    private Double unitOrder;
    private String reOrder;
    private String discount;

    public ProductsRequest(ProductsEntity entity) {
        BeanUtils.copyProperties(entity, this);
        this.id = entity.getId();

        if (entity.getSupplier() != null){
            this.supplierId = entity.getSupplierId();
            this.supplierName = entity.getSupplier().getCompanyName();
        }
    }
}
