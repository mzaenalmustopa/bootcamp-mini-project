package com.dsp.employeeservice.client;

import com.dsp.employeeservice.model.response.DepartmentResponse;
import com.dsp.employeeservice.model.response.EmployeeResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface DepartmentClient {
    @GetExchange("/department/{id}")
    public DepartmentResponse findById(@PathVariable("id") String departmentId);
}
