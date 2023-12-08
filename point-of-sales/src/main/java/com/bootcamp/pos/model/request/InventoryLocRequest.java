package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.InventoryLocEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryLocRequest {

    private String id;
    private String productsId;
    private String locationAddressId;
    private Double quantityStock;
    private Double orderLevel;
    private Double orderQuantity;
    private String averageMonth;
    private String inventoryDetails;
    private String productName;
    private String locationAddressName;

    public InventoryLocRequest(InventoryLocEntity entity) {
        BeanUtils.copyProperties(entity, this);

        if (entity.getProducts() != null){
            this.productsId = entity.getProducts().getId();
            this.productName = entity.getProducts().getProductName();
        }

        if (entity.getAddress() != null){
            this.locationAddressId = entity.getAddress().getId();
            this.locationAddressName = entity.getAddress().getVillage();
        }
    }
}
