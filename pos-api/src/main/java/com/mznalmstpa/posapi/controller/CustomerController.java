package com.mznalmstpa.posapi.controller;

import com.mznalmstpa.posapi.entity.CustomerEntity;
import com.mznalmstpa.posapi.model.CustomerModel;
import com.mznalmstpa.posapi.model.ResponseModel;
import com.mznalmstpa.posapi.service.CostumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v3/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CostumerService service;

    @GetMapping
    private ResponseEntity<ResponseModel> getAll(){
        List<CustomerEntity> data = this.service.getAll();
        return ResponseEntity.ok().body(
                new ResponseModel(200,"Success", data));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ResponseModel> getById(@PathVariable("id") Long id){
        Optional<CustomerEntity> data = this.service.getById(id);
        if (data.isPresent()){
            return ResponseEntity.ok()
                    .body(new ResponseModel(200, "Success", data));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseModel(400,"Failed","Data Not Found"));
    }

    @PostMapping()
    private ResponseEntity<ResponseModel> save(@RequestBody CustomerModel request){
        Optional<CustomerEntity> data = this.service.save(request);
        if (data.isPresent()){
            return ResponseEntity.ok().body(new ResponseModel(200,"Success", data));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseModel(500,"Failed","Save customer Failed"));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<ResponseModel> update(@RequestBody CustomerModel request, @PathVariable("id") Long id){
        Optional<CustomerEntity> data = this.service.update(request, id);
        if (data.isPresent()){
            return ResponseEntity.ok().body(new ResponseModel(200,"Success", data));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseModel(500,"Failed", "Update customer Failed"));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id){
        Optional<CustomerEntity> data = this.service.delete(id);
        if (data.isPresent()){
            return ResponseEntity.ok().body(new ResponseModel(200,"Success", data));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseModel(500,"Failed","Delete customer failed"));
    }
}
