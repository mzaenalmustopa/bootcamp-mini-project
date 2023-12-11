package com.dsp.employeeservice.service;
import com.dsp.employeeservice.model.request.EmployeeRequest;
import com.dsp.employeeservice.model.response.EmployeeResponse;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeResponse> getAll();
    Optional<EmployeeResponse> getById(String id);
    List<EmployeeResponse> getByDepartmentId(String id);
    Optional<EmployeeResponse> save(EmployeeRequest request);
    Optional<EmployeeResponse> update(String id, EmployeeRequest request);
    Optional<EmployeeResponse> delete(String id);
}
