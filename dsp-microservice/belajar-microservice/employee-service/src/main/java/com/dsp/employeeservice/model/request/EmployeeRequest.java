package com.dsp.employeeservice.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    @NotNull
    @Size(max = 100)
    private String name;
    @NotNull
    @Size(min = 36, max = 36)
    private String departmentId;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(max = 100)
    private String position;
    @NotNull
    @Size(max = 10)
    private String gender;
}
