package com.bootcamp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserModel {
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
    private List<String> roles = new ArrayList<>();
}
