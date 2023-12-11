package com.dsp.departmentservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private String id;
    private String code;
    private String name;
    private List<EmployeeResponse> employees;
}
