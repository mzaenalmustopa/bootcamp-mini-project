package com.bootcamp.pos.model.entity;


import com.bootcamp.pos.model.request.ProductSupplierRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_product_suppliers")
public class ProductSuppliersEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "value_supplied")
    private String valueSupplied;

    @Column(name = "total_supplied")
    private String totalSupplied;

    @Column(name = "first_supplied")
    private String firstSupplied;

    @Column(name = "last_supplied")
    private String lastSupplied;

    @Column(name = "delivery")
    private String delivery;

    @Column(name = "standard_price")
    private Double standardPrice;

    @Column(name = "discount")
    private String discount;

    @Column(name = "min_order")
    private Double minOrder;

    @Column(name = "max_order")
    private Double maxOrder;

    @Column(name = "item_supplier_details")
    private String itemSupplierDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductsEntity product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", insertable = false, updatable = false)
    private SuppliersEntity supplier;

    public ProductSuppliersEntity(ProductSupplierRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
