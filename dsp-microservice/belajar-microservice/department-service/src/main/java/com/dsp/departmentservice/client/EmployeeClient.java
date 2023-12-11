package com.dsp.departmentservice.client;

import com.dsp.departmentservice.model.response.EmployeeResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {
    @GetExchange("/employee/department/{id}")
    public List<EmployeeResponse> findByDepartment(@PathVariable("id") String departmentId);
}
