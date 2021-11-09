package com.example.demo.dto;

import lombok.Data;

@Data
public class EmployeeDepartmentDTO {
    private Long employeeID;
    private String employeeName;
    private String departmentName;
    private String message = "this is from DTO (for testing)";
}
