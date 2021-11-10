package com.example.demo.dto;

import com.example.demo.model.Department;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDepartmentDTO {
    private Long employeeID;
    private String employeeName;
    private List<Department> departments;
    private String message = "this is from DTO (for testing)";
}
