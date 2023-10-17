package com.mznalmstpa.posapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    // untuk akses token
    @JsonProperty("access_token")
    private String accessToken;
    // perbaharui token
    @JsonProperty("refresh_token")
    private String refreshToken;
}
