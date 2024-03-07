package mzaenalmstpa.eduprobackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mzaenalmstpa.eduprobackend.constant.MessageApp;
import mzaenalmstpa.eduprobackend.model.request.AuthenticationRequest;
import mzaenalmstpa.eduprobackend.model.request.RegisterRequest;
import mzaenalmstpa.eduprobackend.model.response.Response;
import mzaenalmstpa.eduprobackend.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    @GetMapping("/register")
    public ResponseEntity<Response> register(@RequestBody RegisterRequest request){
        var result = service.register(request);
        return ResponseEntity.ok(
                new Response(HttpStatus.OK.value(), MessageApp.SUCCESS,result)
        );
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody AuthenticationRequest request){
        var result = service.authenticate(request);
        return ResponseEntity.ok(
                new Response(HttpStatus.OK.value(), MessageApp.SUCCESS,result)
        );
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping("/accessDenied")
    public ResponseEntity<Response> accessDenied(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new Response(HttpStatus.FORBIDDEN.value(), MessageApp.FORBIDDEN, null));
    }
}
