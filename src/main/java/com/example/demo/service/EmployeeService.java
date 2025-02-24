package com.example.demo.service;

import com.example.demo.controller.dto.EmployeeDTO;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.persistence.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .name(employeeDTO.getName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .age(employeeDTO.getAge())
                .phone(employeeDTO.getPhone())
                .married(employeeDTO.isMarried())
                .height(employeeDTO.getHeight())
                .dateOfBirth(employeeDTO.getDateOfBirth())
                .department(Department.builder()
                        .name(employeeDTO.getDepartmentDTO().getName())
                        .city(employeeDTO.getDepartmentDTO().getCity())
                        .build())
                .build();
        return this.employeeRepository.save(employee);
    }

}
