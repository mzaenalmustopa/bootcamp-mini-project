package com.mznalmstpa.posapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    //propertu registrasi
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
