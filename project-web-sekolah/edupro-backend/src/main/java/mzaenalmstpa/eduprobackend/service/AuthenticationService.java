package mzaenalmstpa.eduprobackend.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mzaenalmstpa.eduprobackend.model.request.AuthenticationRequest;
import mzaenalmstpa.eduprobackend.model.request.RegisterRequest;
import mzaenalmstpa.eduprobackend.model.response.AuthenticationResponse;

import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
