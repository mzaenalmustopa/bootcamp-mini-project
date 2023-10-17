package com.mznalmstpa.posapi.controller;

import com.mznalmstpa.posapi.entity.SupplierEntity;
import com.mznalmstpa.posapi.model.ResponseModel;
import com.mznalmstpa.posapi.model.SupplierModel;
import com.mznalmstpa.posapi.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3/supplier")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService service;

    @GetMapping
    private ResponseEntity<ResponseModel> getAll(){
        List<SupplierEntity> data = this.service.getAll();
        return ResponseEntity.ok().body(new ResponseModel(200,"Success", data));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ResponseModel> getById(@PathVariable("id") Long id){
        Optional<SupplierEntity> data = this.service.getById(id);
        if (data.isPresent()){
            return ResponseEntity.ok().body(new ResponseModel(200,"Success", data));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseModel(400,"Failed", "Data not Found"));
    }

    @PostMapping
    private ResponseEntity<ResponseModel> save(@RequestBody SupplierModel request, @PathVariable("id")Long id){
        Optional<SupplierEntity> data = this.service.save(request);
        if (data.isPresent()){
            return ResponseEntity.ok().body(new ResponseModel(200,"Success" , data));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseModel(500,"Failed","Save Supplier Failed"));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<ResponseModel> update(@RequestBody SupplierModel request, @PathVariable("id")Long id){
        Optional<SupplierEntity> data = this.service.update(request, id);
        if (data.isPresent()){
            return ResponseEntity.ok().body(new ResponseModel(200,"Success",data));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseModel(500,"Failed","Update Supplier Failed"));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id){
        Optional<SupplierEntity> data = this.service.delete(id);
        if (data.isPresent()){
            return ResponseEntity.ok().body(new ResponseModel(200,"Success", data));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseModel(500,"Failed","Delete Supplier Failed"));
    }
}
