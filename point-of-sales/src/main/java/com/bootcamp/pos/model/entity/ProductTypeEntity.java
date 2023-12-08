package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.ProductTypeRequest;
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
@Table(name = "tbl_refproduct_type")
public class ProductTypeEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "product_type_code")
    private String productTypeCode;

    @Column(name = "product_type_desc")
    private String productTypeDesc;

    public ProductTypeEntity(ProductTypeRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
