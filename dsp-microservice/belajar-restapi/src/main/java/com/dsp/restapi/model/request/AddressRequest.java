package com.dsp.restapi.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    @NotNull
    @Size(min = 5, max = 100)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 100)
    private String street;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 100)
    private String city;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 100)
    private String province;

    @NotNull
    @Size(min = 6, max = 100)
    private String country;

    @NotNull
    @Size(min = 5, max = 10)
    private String postalCode;
}
