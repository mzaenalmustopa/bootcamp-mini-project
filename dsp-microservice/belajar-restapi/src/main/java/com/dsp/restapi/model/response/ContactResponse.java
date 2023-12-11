package com.dsp.restapi.model.response;

import com.dsp.restapi.model.request.AddressRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {
    private String id;
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private String phone;
    private List<AddressResponse> address;
}
