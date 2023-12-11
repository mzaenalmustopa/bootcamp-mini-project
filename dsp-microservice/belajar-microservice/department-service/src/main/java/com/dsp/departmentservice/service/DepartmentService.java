package com.dsp.departmentservice.service;

import com.dsp.departmentservice.model.request.DepartmentRequest;
import com.dsp.departmentservice.model.response.DepartmentResponse;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<DepartmentResponse> getAll();
    Optional<DepartmentResponse> getById(String id);
    Optional<DepartmentResponse> save(DepartmentRequest request);
    Optional<DepartmentResponse> update(String id, DepartmentRequest request);
    Optional<DepartmentResponse> delete(String id);
}
