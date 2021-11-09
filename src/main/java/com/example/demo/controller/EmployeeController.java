package com.example.demo.controller;


import com.example.demo.dto.EmployeeDepartmentDTO;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final ModelMapper modelMapper;

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDepartmentDTO>> getAll() {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDepartmentDTO> getEmployeeById(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        // convert entity to DTO
        EmployeeDepartmentDTO employeeResponse = modelMapper.map(employee, EmployeeDepartmentDTO.class);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }


    @PostMapping("/employees")
    public ResponseEntity<EmployeeDepartmentDTO> createEmployee(@RequestBody Employee newEmployee) {
        Employee employee = employeeService.addNewEmployee(newEmployee);
        // convert entity to DTO
        return new ResponseEntity<>(modelMapper.map(employee, EmployeeDepartmentDTO.class), HttpStatus.CREATED);
    }


    @PutMapping("/employees/{employeeID}")
    void updateEmployee(@PathVariable("employeeID") Long id, @RequestParam(required = false) String name){
        employeeService.updateStudent(id, name);
    }


    @DeleteMapping("/employees/{employeeID}")
    public ResponseEntity deleteStudent(@PathVariable("employeeID") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("delete successful", HttpStatus.OK);
    }

    /**
     * this is for testing purpose. use it when comparing DTO and ModelMapper and ResponseEntity
     * @return
     */
    @GetMapping("/employees/showeverything")
    public List<Employee> getEverything(){
        return employeeService.getEmployeesEverything();
    }

//    /**
//     * basic test
//     */
//    @GetMapping("/")
//    public String index(){
//        return "Greetings from Spring Boot 你好";
//    }
}

