package com.example.demo.repository;

import com.example.demo.model.Employee_Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employee_DepartmentRepository extends JpaRepository<Employee_Department, Long> {
}
