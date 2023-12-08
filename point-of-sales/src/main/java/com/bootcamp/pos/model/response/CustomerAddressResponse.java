package com.bootcamp.pos.model.response;

import com.bootcamp.pos.model.entity.CustomerAddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressResponse {
    private String id;
    private String customerId;
    private String addressId;
    private String dateFromId;
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate dateTo;
    private String customerName;
    private String addressName;
    private String dateFromName;
}
