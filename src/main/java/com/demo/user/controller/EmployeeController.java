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
    public AppResponse<List<Employee>> addEmployeeList() {
        List<Employee> employees = employeeService.addEmployeeList();
        return new AppResponse<>(employees);
    }

    @GetMapping("/list/db")
    public AppResponse<Void> createEmployeeList() {
        employeeService.createEmployeeListAsync();
        return new AppResponse<>();
    }

    @GetMapping("/{id}")
    public AppResponse<Employee> findEmployee(@PathVariable int id) {
        return new AppResponse<>(employeeService.findEmployeeById(id));
    }

    @PostMapping("report")
    public AppResponse<Long> validateEmployees() {
        return new AppResponse<>(employeeService.validateEmployees());
    }

}
