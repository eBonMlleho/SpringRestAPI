package com.example.demo.controller;


import com.example.demo.dto.EmployeeDepartmentDTO;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final ModelMapper modelMapper;

    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDepartmentDTO>> getAll(){
        try{
            // DTO logic is in service layer
            return new ResponseEntity<>(employeeService.getEmployeesDTO(), HttpStatus.OK);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDepartmentDTO> getEmployeeById(@PathVariable(name = "id") Long id) {
        try{
            Employee employee = employeeService.getEmployeeById(id);
            // convert entity to DTO by using modelMapper
            EmployeeDepartmentDTO employeeResponse = modelMapper.map(employee, EmployeeDepartmentDTO.class);
            return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


    @PostMapping()
    public ResponseEntity<EmployeeDepartmentDTO> createEmployee(@RequestBody Employee newEmployee) {
        try{
            Employee employee = employeeService.addNewEmployee(newEmployee);
            // convert entity to DTO
            return new ResponseEntity<>(modelMapper.map(employee, EmployeeDepartmentDTO.class), HttpStatus.CREATED);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PostMapping("{departmentID}")
        public ResponseEntity<EmployeeDepartmentDTO> createEmployeeWithDepartment(
                @PathVariable("departmentID") Long departmentID, @RequestBody Employee employee) {
        try{
            employeeService.createEmployeeWithDepartment(employee, departmentID);
            // convert entity to DTO
            return new ResponseEntity<>(modelMapper.map(employee, EmployeeDepartmentDTO.class), HttpStatus.CREATED);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


    @PutMapping("/{employeeID}") // http://localhost:8080/employees/4?name=Faker
    ResponseEntity updateEmployee(@PathVariable("employeeID") Long id, @RequestParam(required = false) String name){
        try{
            employeeService.updateEmployee(id, name);
            return new ResponseEntity("update employee name successful!", HttpStatus.OK);
        }catch(Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


    @PutMapping("/{employeeID}/department/{departmentID}")
    ResponseEntity updateEmployeeWithDepartment(@PathVariable("employeeID") Long employeeID,
                                      @PathVariable("departmentID") Long departmentID,
                                      @RequestParam(required = false) String name){
        try{
            employeeService.updateEmployeeDepartment(employeeID, departmentID);
            return new ResponseEntity("Assign employee to department operation successful!", HttpStatus.OK);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


    @DeleteMapping("/{employeeID}")
    public ResponseEntity deleteStudent(@PathVariable("employeeID") Long id){
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("delete successful", HttpStatus.OK);
        }catch(Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


    /**
     * this is for testing purpose. use it when comparing DTO and ModelMapper and ResponseEntity
     * @return
     */
    @GetMapping("/showeverything")
    public List<Employee> getEverything(){
        return employeeService.getEmployeesEverything();
    }
}

