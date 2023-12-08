package com.bootcamp.pos.model.response;

import com.bootcamp.pos.model.entity.InventoryLocEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryLocResponse {

    private String id;
    private String productsId;
    private String locationAddressId;
    private Double quantityStock;
    private Double orderLevel;
    private Double orderQuantity;
    private String averageMonth;
    private String inventoryDetails;
}
