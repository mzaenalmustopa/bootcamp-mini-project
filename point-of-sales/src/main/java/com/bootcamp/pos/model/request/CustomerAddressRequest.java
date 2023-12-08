package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.CustomerAddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressRequest {
    private String id;
    private String customerId;
    private String addressId;
    private String dateFromId;
    private String addressTypeId;
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate dateTo;
    private String customerName;
    private String addressName;
    private String addressTypeName;
    private String dateFromName;

    public CustomerAddressRequest(CustomerAddressEntity entity) {
        BeanUtils.copyProperties(entity , this);

        if (entity.getCustomer() != null){
            this.customerId = entity.getCustomer().getId();
            this.customerName = entity.getCustomer().getCustomerName();
        }

        if (entity.getAddress() != null){
            this.addressId = entity.getAddress().getId();
            this.addressName = entity.getAddress().getStreet();
        }

        if (entity.getAddressType() != null){
            this.addressTypeId = entity.getAddressType().getId();
            this.addressTypeName = entity.getAddressType().getAddressCodeType();
        }

        if (entity.getSupplierLoc() != null){
            this.dateFromId = entity.getSupplierLoc().getId();
            this.dateFromName = entity.getSupplierLoc().getDateFrom();
        }
    }
}
