package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }


    public List<Department> getDepartmentEverything(){
        return departmentRepository.findAll();
    }

    public Department getDepartmentByID(long id) {
        Optional<Department> result = departmentRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        } else {
            throw new IllegalStateException("id does not exist");
        }
    }
}
