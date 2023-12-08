package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.ProductSuppliersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSupplierRequest {
    private String id;
    private String productId;
    private String supplierId;
    private String valueSupplied;
    private String totalSupplied;
    private String firstSupplied;
    private String lastSupplied;
    private String delivery;
    private Double standardPrice;
    private String discount;
    private Double minOrder;
    private Double maxOrder;
    private String itemSupplierDetails;
    private String productName;
    private String supplierName;

    public ProductSupplierRequest(ProductSuppliersEntity entity) {
        BeanUtils.copyProperties(entity , this);

        if (entity.getProduct() != null){
            this.productId = entity.getProduct().getId();
            this.productName = entity.getProduct().getProductName();
        }

        if (entity.getSupplier() != null){
            this.supplierId = entity.getSupplier().getId();
            this.supplierName = entity.getSupplier().getSupplierCode();
        }
    }
}
