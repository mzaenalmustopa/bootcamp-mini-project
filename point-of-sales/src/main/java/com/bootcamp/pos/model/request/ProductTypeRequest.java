package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.ProductTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeRequest {
    private String id;
    private String productTypeCode;
    private String productTypeDesc;

    public ProductTypeRequest(ProductTypeEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
