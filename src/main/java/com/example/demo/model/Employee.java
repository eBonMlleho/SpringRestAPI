package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String secret;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee_Department> employee_departments = new ArrayList<>();

    public List<Employee_Department> getEmployee_departments(){
        return employee_departments;
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, String email, String secret) {
        this.name = name;
        this.email = email;
        this.secret = secret;
    }
}
