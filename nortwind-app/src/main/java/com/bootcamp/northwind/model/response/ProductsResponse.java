package com.bootcamp.northwind.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponse {
    private Long id;
    private String productName;
    private String supplierId;
    private String categoryId;
    private Double quantity;
    private Double price;
    private Double stock;
    private Double unitOrder;
    private String reorder;
    private String discount;
}
