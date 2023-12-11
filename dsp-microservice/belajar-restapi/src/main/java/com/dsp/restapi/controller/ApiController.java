package com.dsp.restapi.controller;

import com.dsp.restapi.model.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @RequestMapping("/ping")
    public ResponseEntity<Response> ping(){
        return ResponseEntity.ok(
                new Response(200,"Success","Pong..!")
        );
    }
}
