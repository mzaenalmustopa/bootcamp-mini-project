package com.bootcamp.northwind.model.request;

import com.bootcamp.northwind.model.entity.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest {
    private Long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
    private String homepage;

    public SupplierRequest(SupplierEntity entity) {
        BeanUtils.copyProperties(entity, this);
        this.id = entity.getId();
    }
}
