package com.bootcamp.pos.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSupplierResponse {
    private String id;
    private String productId;
    private String supplierId;
    private String supplierCode;
    private String valueSupplied;
    private String totalSupplied;
    private String firstSupplied;
    private String lastSupplied;
    private String delivery;
    private String standardPrice;
    private String discount;
    private Double minOrder;
    private Double maxOrder;
    private String itemSupplierDetails;
    private String productName;
    private String supplierName;
}
