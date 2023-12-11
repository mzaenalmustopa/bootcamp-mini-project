package com.dsp.employeeservice.controller;

import com.dsp.employeeservice.client.DepartmentClient;
import com.dsp.employeeservice.model.request.EmployeeRequest;
import com.dsp.employeeservice.model.response.DepartmentResponse;
import com.dsp.employeeservice.model.response.EmployeeResponse;
import com.dsp.employeeservice.model.response.Response;
import com.dsp.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService service;
    private DepartmentClient departmentClient;

    @Autowired
    public EmployeeController(EmployeeService service, DepartmentClient departmentClient) {
        this.service = service;
        this.departmentClient = departmentClient;
    }

    @GetMapping
    public ResponseEntity<Response> getAll(){
        var result = this.service.getAll();
        return ResponseEntity.ok(
                new Response(200, "Success", result)
        );
    }

    @GetMapping("/with-department")
    public ResponseEntity<Response> getWithDepartment(){
        var result = this.service.getAll();
        result.forEach(emp -> {
            emp.setDepartment(departmentClient.findById(emp.getDepartmentId()));
        });
        return ResponseEntity.ok(
                new Response(200, "Success", result)
        );
    }

    @GetMapping("/department/{id}")
    public List<EmployeeResponse> getByDepartmentId(@PathVariable("id") String id){
        var result = this.service.getByDepartmentId(id);
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable("id") String id){
        var result = this.service.getById(id).orElse(null);
        if(result != null){
            DepartmentResponse department = departmentClient.findById(result.getDepartmentId());
            result.setDepartment(department);
        }
        return ResponseEntity.ok(
                new Response(200, "Success", result)
        );
    }

    @PostMapping
    public ResponseEntity<Response> create(
            @RequestBody @Valid EmployeeRequest request
            ){
        var result = this.service.save(request).orElse(null);
        if(result == null){
            return ResponseEntity.ok(
                    new Response(500, "Failed", null)
            );
        }
        return ResponseEntity.ok(
                new Response(200, "Success", result)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> update(
            @PathVariable("id") String id,
            @RequestBody @Valid EmployeeRequest request){
        var result = this.service.update(id,request).orElse(null);
        if(result == null){
            return ResponseEntity.ok(
                    new Response(500, "Failed", null)
            );
        }
        return ResponseEntity.ok(
                new Response(200, "Success", result)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") String id){
        var result = this.service.delete(id).orElse(null);
        if(result == null){
            return ResponseEntity.ok(
                    new Response(500, "Failed", null)
            );
        }
        return ResponseEntity.ok(
                new Response(200, "Success", result)
        );
    }
}
