package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.AddressTypeEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressTypeRequest {

    private String id;
    @NotBlank(message = "Address Code Tidak Boleh Kosong")
    private String addressCodeType;
    @NotBlank(message = "Address Desc Tidak Boleh Kosong")
    private String addressDescType;
    @NotBlank(message = "Billing Tidak Boleh Kosong")
    private String billing;
    @NotBlank(message = "Delivery Tidak Boleh Kosong")
    private String delivery;

    public AddressTypeRequest(AddressTypeEntity result) {
        BeanUtils.copyProperties(result, this);
    }
}
