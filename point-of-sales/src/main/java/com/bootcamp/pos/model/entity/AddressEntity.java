package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.AddressRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_address")
public class AddressEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "no_home", length = 60)
    private String noHome;

    @Column(name = "street", length = 60)
    private String street;

    @Column(name = "village", length = 60)
    private String village;

    @Column(name = "sub_district", length = 100)
    private String subDistrict;

    @Column(name = "regency", length = 100)
    private String regency;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "province", length = 100)
    private String province;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "postal_code", length = 100)
    private String postalCode;

    @Column(name = "other_details", length = 100)
    private String otherDetails;

    public AddressEntity(AddressRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
