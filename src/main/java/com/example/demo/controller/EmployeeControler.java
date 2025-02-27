package com.example.demo.controller;

import com.example.demo.controller.dto.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class EmployeeControler {

    private final EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(this.employeeService.saveEmployee(employeeDTO),
                HttpStatus.CREATED);
    }

}
