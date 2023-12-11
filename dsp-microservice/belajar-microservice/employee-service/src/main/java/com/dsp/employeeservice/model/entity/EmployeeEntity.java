package com.dsp.employeeservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_employee")
public class EmployeeEntity {
    @Id
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;
    @Column(name = "employee_name", length = 100, nullable = false)
    private String name;
    @Column(name = "department_id", length = 36, nullable = false)
    private String departmentId;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "position", length = 100)
    private String position;
    @Column(name = "gender", length = 10)
    private String gender;
}
