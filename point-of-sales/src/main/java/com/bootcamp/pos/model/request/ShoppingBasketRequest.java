package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.ShoppingBasketEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingBasketRequest {
    private String id;
    private String customerId;
    private String basketDateTime;
    private Double totalCost;
    private String otherDetails;
    private String customerName;

    public ShoppingBasketRequest(ShoppingBasketEntity entity) {
        BeanUtils.copyProperties(entity, this);

        if (entity.getCustomer() != null){
            this.customerId = entity.getCustomer().getId();
            this.customerName = entity.getCustomer().getCustomerName();
        }
    }
}
