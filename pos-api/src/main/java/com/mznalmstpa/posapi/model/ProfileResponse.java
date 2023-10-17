package com.mznalmstpa.posapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    //property profile response
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;
}
