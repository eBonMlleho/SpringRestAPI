package com.example.demo.model;

import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.Employee_DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeDepartmentConfig {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository,
                                        Employee_DepartmentRepository edRepository,
                                        DepartmentRepository departmentRepository){
        return args -> {
            Department d1 = new Department("Marketing");
            Department d2 = new Department("Sales");
//            departmentRepository.saveAll(List.of(d1, d2 ));

            // employee test only.
            Employee bella = new Employee("bella", "bella@gmail.com","this isBella secret");
            Employee ebon = new Employee("ebon", "ebon@gmail.com", "ebon's secret");
            Employee luke = new Employee("luke", "luke@gmail.com", "luke's gay");
            employeeRepository.saveAll(List.of(bella, ebon, luke));



//             many to many init
//            Employee bella = new Employee("bella", "bella@gmail.com","this isBella secret");
//            Employee ebon = new Employee("ebon", "ebon@gmail.com", "ebon's secret");
//            Employee luke = new Employee("luke", "luke@gmail.com", "luke's gay");
//            Employee bella = new Employee("bella", new Department("Marketing"), new Department("Sales"));
//            Employee ebon = new Employee("ebon", new Department("Marketing"));
//            Employee luke = new Employee("luke", new Department("Sales"));
//            Employee_Department ed1 = new Employee_Department(bella, d1);
//            edRepository.save(ed1);
//            employeeRepository.saveAll(List.of(bella, ebon, luke));
        };
    }
}
