package com.mznalmstpa.springjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_customer_address")
public class CustomerAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ADDR_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @Column(name = "ADDRESS", length = 128)
    private String address;

    @Column(name = "DISTRICT", length = 128)
    private String district;

    @Column(name = "CITY", length = 128)
    private String city;

    @Column(name = "PROVINCE", length = 128)
    private String province;

    @Column(name = "COUNTRY", length = 128)
    private String country;
}
