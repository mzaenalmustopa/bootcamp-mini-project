package com.dsp.restapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private String id;
    private String name;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
}
