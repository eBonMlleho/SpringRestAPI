package com.example.demo.service;

import com.example.demo.dto.EmployeeDepartmentDTO;
import com.example.demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDepartmentDTO> getEmployeesDTO();

    Employee getEmployeeById(long id);

    Employee addNewEmployee(Employee employee);

    void deleteEmployee(Long employeeID);

    void updateEmployee(long employeeID, String name);

    void updateEmployeeDepartment(Long employeeID, Long departmentID);

    void createEmployeeWithDepartment(Employee employee, Long departmentID);

}
