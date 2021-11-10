package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.name = ?1")
    Optional<Employee> findEmployeeByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.id = ?1")
    Optional<Employee> findEmployeeById(Long id);

}
