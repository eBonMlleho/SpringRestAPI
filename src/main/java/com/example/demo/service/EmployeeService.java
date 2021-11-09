package com.example.demo.service;


import com.example.demo.dto.EmployeeDepartmentDTO;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // this method used convertEntityToDTO method to populate DTO from employees
    public List<EmployeeDepartmentDTO> getEmployees(){
        return employeeRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

//  this is original get all method without DTO and this is for testing purpose only
    public List<Employee> getEmployeesEverything(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        Optional<Employee> result = employeeRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new IllegalStateException("id does not exist");
        }
    }


    public Employee addNewEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByName(employee.getName());

        if(employeeOptional.isPresent()){
            throw new IllegalStateException("name is taken");
        }
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteEmployee(Long employeeID) {
        if(!employeeRepository.existsById(employeeID)){
            throw new IllegalStateException("employee ID:" + employeeID + " does not exist");
        }
        employeeRepository.deleteById(employeeID);
    }

    @Transactional // repository 里面就不用 写 query 了
    public void updateStudent(long employeeID, String name) {
        Employee employee = employeeRepository.findById(employeeID)
                .orElseThrow(()->new IllegalStateException("employee ID:" + employeeID + " does not exist"));

        if(name != null && name.length() > 0){
            employee.setName(name);
        }

    }

    private EmployeeDepartmentDTO convertEntityToDTO(Employee employee){
        EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO();
        employeeDepartmentDTO.setEmployeeID(employee.getId());
        employeeDepartmentDTO.setEmployeeName(employee.getName());
//        employeeDepartmentDTO.setDepartmentName(employee.getDepartmentName());
        return employeeDepartmentDTO;
    }



}