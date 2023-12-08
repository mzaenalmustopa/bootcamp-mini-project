package com.bootcamp.pos.model.entity;

import com.bootcamp.pos.model.request.SupplierLocRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_supplier_loc")
public class SupplierLocEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "supplier_code_id")
    private String supplierCodeId;

    @Column(name = "address_id")
    private String addressId;

    @Column(name = "date_from_")
    private String dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_code_id", insertable = false, updatable = false)
    private SuppliersEntity suppliers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private AddressEntity address;

    public SupplierLocEntity(SupplierLocRequest request) {
        BeanUtils.copyProperties(request, this);
        this.id = UUID.randomUUID().toString();
    }
}
