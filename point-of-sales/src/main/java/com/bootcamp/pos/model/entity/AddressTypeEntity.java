package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.AddressTypeRequest;
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
@Table(name = "tbl_refaddress")
public class AddressTypeEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "address_code_type")
    private String addressCodeType;

    @Column(name = "address_desc_type")
    private String addressDescType;

    @Column(name = "billing")
    private String billing;

    @Column(name = "delivery")
    private String delivery;

    public AddressTypeEntity(AddressTypeRequest request) {
        BeanUtils.copyProperties(request , this);
        this.id = UUID.randomUUID().toString();
    }
}
