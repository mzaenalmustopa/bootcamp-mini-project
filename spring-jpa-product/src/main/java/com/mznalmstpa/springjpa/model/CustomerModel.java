package com.mznalmstpa.springjpa.model;

import com.mznalmstpa.springjpa.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    private Long id;
    private Long accountNo;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;


    // dependency injection
    public CustomerModel(CustomerEntity entity) {
        this.id = entity.getId();
        this.accountNo = entity.getAccountNo();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
    }
}
