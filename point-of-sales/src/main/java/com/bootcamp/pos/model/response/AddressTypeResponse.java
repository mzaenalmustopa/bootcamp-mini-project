package com.bootcamp.pos.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressTypeResponse {

    private String id;
    private String addressTypeCode;
    private String addressDescType;
    private String billing;
    private String delivery;
}
