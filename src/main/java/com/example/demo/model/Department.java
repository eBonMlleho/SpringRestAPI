package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Employee_Department> department_employees = new ArrayList<>();

//    public List<Employee_Department> getDepartment_employees(){
//        return department_employees;
//    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "departments")
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }


}
