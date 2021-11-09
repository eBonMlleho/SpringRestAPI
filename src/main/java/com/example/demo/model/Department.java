package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToMany(mappedBy = "departments")
    private Set<Employee> employees = new HashSet<>();




    public Department(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }


}
