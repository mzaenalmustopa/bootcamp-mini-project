package com.mznalmstpa.posapi.service;

import com.mznalmstpa.posapi.model.AuthRequest;
import com.mznalmstpa.posapi.model.AuthResponse;
import com.mznalmstpa.posapi.model.ProfileResponse;
import com.mznalmstpa.posapi.model.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public interface AuthService {
//    list dari auth response dll
    Optional<AuthResponse> authenticate(AuthRequest request);
    Optional<AuthResponse> register(RegisterRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    Optional<ProfileResponse> profile(HttpServletRequest request, HttpServletResponse response);
}
