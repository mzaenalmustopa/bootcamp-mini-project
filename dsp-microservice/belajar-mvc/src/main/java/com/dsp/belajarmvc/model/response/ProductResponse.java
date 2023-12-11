package com.dsp.belajarmvc.model.response;

import com.dsp.belajarmvc.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String code;
    private String name;
    private Date expireDate;
    private Long price;
    private Long categoryId;
    private String categoryName;

    public ProductResponse(ProductEntity entity) {
        BeanUtils.copyProperties(entity, this);
        if(entity.getCategory() != null){
            this.categoryId = entity.getCategory().getId();
            this.categoryName = entity.getCategory().getName();
        }
    }
}
