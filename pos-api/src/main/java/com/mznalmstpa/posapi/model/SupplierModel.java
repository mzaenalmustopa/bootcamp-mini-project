package com.mznalmstpa.posapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierModel {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String homepage;
}
