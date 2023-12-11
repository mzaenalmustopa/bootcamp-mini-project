package com.dsp.restapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_contact")
@NoArgsConstructor
@AllArgsConstructor
public class ContactEntity {
    @Id
    @Column(name = "id", length = 36, unique = true, nullable = false)
    private String id;

    @Column(name = "fist_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "phone_number", length = 20)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> address = new ArrayList<>();

    public void addAddress(AddressEntity addressEntity){
        this.address.add(addressEntity);
        addressEntity.setContact(this);
    }
}
