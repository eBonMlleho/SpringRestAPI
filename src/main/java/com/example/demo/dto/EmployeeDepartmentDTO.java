package com.example.demo.dto;

import com.example.demo.model.Department;
import lombok.Data;

import java.util.Set;

@Data
public class EmployeeDepartmentDTO {
    private Long employeeID;
    private String employeeName;
    private Set<Department> departments;
    private String message = "this is from DTO (for testing)";
}
