package com.dsp.restapi.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotNull
    @Size(max = 100, min = 6, message = "Username min 6 and max 100")
    private String username;

    @NotNull
    @Size(max = 100, min = 6, message = "Password min 6 and max 100")
    private String password;

    @NotNull
    @Email
    private String email;
}
