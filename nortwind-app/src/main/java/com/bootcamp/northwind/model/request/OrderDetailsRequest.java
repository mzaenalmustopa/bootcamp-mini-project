package com.bootcamp.northwind.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsRequest {
    private Long id;
    private String orderId;
    private String productId;
    private Double price;
    private Double quantity;
    private String discount;
}
