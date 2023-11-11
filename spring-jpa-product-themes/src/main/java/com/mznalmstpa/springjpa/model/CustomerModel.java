package com.mznalmstpa.springjpa.model;

import com.mznalmstpa.springjpa.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    private Long id;
    private Long accountNo;
    private String name;
    private String email;
    private String phoneNumber;
    private List<CustomerAddressModel> address = new ArrayList<>();


    // dependency injection
    public CustomerModel(CustomerEntity entity) {
        this.id = entity.getId();
        this.accountNo = entity.getAccountNo();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();

        if (!entity.getAddress().isEmpty()){
            this.address = entity.getAddress().stream()
                    .map(CustomerAddressModel::new)
                    .collect(Collectors.toList());
        }
    }
}
