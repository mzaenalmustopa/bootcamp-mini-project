package com.mznalmstpa.springjpa.model;

import com.mznalmstpa.springjpa.entity.CustomerAddressEntity;
import com.mznalmstpa.springjpa.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressModel {
    private Long id;
    private CustomerEntity customer;
    private String address;
    private String district;
    private String city;
    private String province;
    private String country;

    // dependency injection
    public CustomerAddressModel(CustomerAddressEntity entity) {
        this.id = entity.getId();
        this.customer = entity.getCustomer();
        this.address = entity.getAddress();
        this.district = entity.getDistrict();
        this.city = entity.getCity();
        this.province = entity.getProvince();
        this.country = entity.getCountry();
    }
}
