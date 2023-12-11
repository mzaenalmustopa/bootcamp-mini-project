package com.dsp.restapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_address")
public class AddressEntity {
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;
    @Column(name = "address_name", length = 128, nullable = false)
    private String name;
    @Column(name = "street", length = 128, nullable = false)
    private String street;
    @Column(name = "city", length = 128, nullable = false)
    private String city;
    @Column(name = "province", length = 128, nullable = false)
    private String province;
    @Column(name = "country", length = 128, nullable = false)
    private String country;
    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private ContactEntity contact;
}
