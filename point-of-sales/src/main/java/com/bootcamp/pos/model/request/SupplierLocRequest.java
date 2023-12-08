package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.SupplierLocEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierLocRequest {
    private String id;
    private String supplierCodeId;
    private String addressId;
    private String dateFrom;
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate dateTo;
    private String supplierCodeName;
    private String addressName;

    public SupplierLocRequest(SupplierLocEntity entity) {
        BeanUtils.copyProperties(entity, this);

        if (entity.getSuppliers() != null){
            this.supplierCodeId = entity.getSuppliers().getId();
            this.supplierCodeName = entity.getSuppliers().getSupplierCode();
        }

        if (entity.getAddress() != null){
            this.addressId = entity.getAddress().getId();
            this.addressName = entity.getAddress().getVillage();
        }
    }
}
