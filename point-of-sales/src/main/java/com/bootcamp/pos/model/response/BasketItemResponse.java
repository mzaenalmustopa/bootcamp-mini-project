package com.bootcamp.pos.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemResponse {

    private String id;
    private String customerId;
    private String  productId;
    private String basketDateTimeId;
    private Double quantity;
    private Double cost;
}
