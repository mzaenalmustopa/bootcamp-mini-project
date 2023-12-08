package com.bootcamp.pos.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String id;
    private String productTypeCodeId;
    private String productDetails;
    private String productName;
    private Double productPrice;
    private String description;
    private String productTypeCodeName;
}
