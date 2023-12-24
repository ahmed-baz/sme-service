package com.demo.user.controller;

import com.demo.user.exception.AppResponse;
import com.demo.user.model.Employee;
import com.demo.user.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/list/file")
    public List<Employee> addEmployeeList() {
        return employeeService.addEmployeeList();
    }

    @GetMapping("/list/db")
    public AppResponse<Void> createEmployeeList() {
        employeeService.createEmployeeListAsync();
        return new AppResponse<>();
    }

    @GetMapping("/{id}")
    public Employee findEmployee(@PathVariable int id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping("report")
    public long validateEmployees() {
        return employeeService.validateEmployees();
    }

}
