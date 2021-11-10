package com.example.demo.service;


import com.example.demo.dto.EmployeeDepartmentDTO;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
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
        Optional<Employee> result = employeeRepository.findEmployeeById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new IllegalStateException("id does not exist");
        }
    }


    public Employee addNewEmployee(Employee employee) {
//        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByName(employee.getName());
//
//        if(employeeOptional.isPresent()){
//            throw new IllegalStateException("name is taken");
//        }
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
    public void updateEmployee(long employeeID, String name) {
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
        employeeDepartmentDTO.setDepartments(employee.getDepartments());
        return employeeDepartmentDTO;
    }


    public void updateEmployeeDepartment(Long employeeID, Long departmentID) {
        Employee employee = employeeRepository.findById(employeeID)
                .orElseThrow(()->new IllegalStateException("employee ID:" + employeeID + " does not exist"));
        Department department = departmentRepository.findById(departmentID)
                .orElseThrow(()->new IllegalStateException("department ID:" + departmentID + " does not exist"));
        employee.enrollDepartment(department);
        employeeRepository.save(employee);
    }

    public void createEmployeeWithDepartment(Employee employee, Long departmentID) {
        addNewEmployee(employee);
        updateEmployeeDepartment(employee.getId(), departmentID);
    }
}
