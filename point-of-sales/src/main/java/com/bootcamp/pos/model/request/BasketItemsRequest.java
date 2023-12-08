package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.BasketItemsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemsRequest {

    private String id;
    private String customerId;
    private String basketDateTimeId;
    private String productsId;
    private Double quantity;
    private Double cost;
    private String customerName;
    private String basketDateTimeName;
    private String productName;

    public BasketItemsRequest(BasketItemsEntity result) {
        BeanUtils.copyProperties(result, this);

        if (result.getCustomer() != null){
            this.customerId = result.getCustomer().getId();
            this.customerName = result.getCustomer().getCustomerName();
        }

        if (result.getShoppingBasket() != null){
            this.basketDateTimeId = result.getShoppingBasket().getId();
            this.basketDateTimeName = result.getShoppingBasket().getBasketDateTime();
        }

        if (result.getProducts() != null){
            this.productsId = result.getProducts().getId();
            this.productName = result.getProducts().getProductName();
        }
    }
}
