package com.dsp.restapi.controller;

import com.dsp.restapi.model.request.RegisterRequest;
import com.dsp.restapi.model.response.Response;
import com.dsp.restapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody @Valid RegisterRequest request){
        Response result = service.register(request).orElse(null);
        return ResponseEntity.ok(result);
    }

    @GetMapping("")
    public ResponseEntity<Response> list(){
        Response result = service.get().orElse(null);
        return ResponseEntity.ok(result);
    }
}
