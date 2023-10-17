package com.mznalmstpa.posapi.controller;

import com.mznalmstpa.posapi.model.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/")
public class HomeController {
    // test resquest
    @GetMapping("test")
    public ResponseEntity<ResponseModel> getTest(){
        return ResponseEntity.ok()
                .body(new ResponseModel(200,"SUCCESS","Welcome to POS API"));
    }
}
