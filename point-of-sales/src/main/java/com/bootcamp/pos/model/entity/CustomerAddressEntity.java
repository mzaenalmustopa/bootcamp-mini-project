package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.CustomerAddressRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_customer_address")
public class CustomerAddressEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "address_id")
    private String addressId;

    @Column(name = "date_from_id")
    private String dateFromId;

    @Column(name = "address_type_id")
    private String addressTypeId;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", insertable = false , updatable = false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private AddressEntity address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_type_id", insertable = false, updatable = false)
    private AddressTypeEntity addressType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "date_from_id", insertable = false, updatable = false)
    private SupplierLocEntity supplierLoc;

    public CustomerAddressEntity(CustomerAddressRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
