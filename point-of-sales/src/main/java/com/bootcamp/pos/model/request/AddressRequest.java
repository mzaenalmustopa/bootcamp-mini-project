package com.bootcamp.pos.model.request;

import com.bootcamp.pos.model.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    private String id;
    private String noHome;
    private String street;
    private String village;
    private String subDistrict;
    private String regency;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String otherDetails;

    public AddressRequest(AddressEntity addressEntity) {
        BeanUtils.copyProperties(addressEntity, this);
    }
}
