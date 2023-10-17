package com.mznalmstpa.posapi.controller;

import com.mznalmstpa.posapi.model.*;
import com.mznalmstpa.posapi.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //log in
    @PostMapping("/authenticate")
    public ResponseEntity<ResponseModel> authenticate(@RequestBody AuthRequest request){
        Optional<AuthResponse> result = authService.authenticate(request);
        if(result.isPresent()) {
            return ResponseEntity.ok()
                    .body(new ResponseModel(200, "SUCCESS", result));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseModel(400,"FAILED","User Not found"));
    }

    //registrasi
    @PostMapping("/register")
    public ResponseEntity<ResponseModel> register(@RequestBody RegisterRequest request) {
        Optional<AuthResponse> result = authService.register(request);

        if(result.isPresent()) {
            return ResponseEntity.ok()
                    .body(new ResponseModel(200, "SUCCESS", result));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseModel(400,"FAILED","Register is failed"));
    }

    //refresh token
    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }


    // profil
    @GetMapping("/profile")
    public ResponseEntity<ResponseModel> getProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Optional<ProfileResponse> result = authService.profile(request, response);
        if(result.isPresent()) {
            return ResponseEntity.ok()
                    .body(new ResponseModel(200, "SUCCESS", result));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseModel(500,"FAILED","User not found"));
    }
}
