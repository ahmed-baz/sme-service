package com.demo.user.controller;

import com.demo.user.vo.AppResponse;
import com.demo.user.model.Employee;
import com.demo.user.service.EmployeeService;
import com.demo.user.service.UserExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserExecutorService userExecutorService;

    @GetMapping("/list/file")
    public AppResponse<List<Employee>> addEmployeeList() {
        List<Employee> employees = employeeService.addEmployeeList();
        return new AppResponse<>(employees);
    }

    @GetMapping("/list/db/{size}")
    public AppResponse<List<Employee>> createEmployeeList(@PathVariable int size) {
        List<Employee> employeeList = userExecutorService.createEmployeeList(size);
        return new AppResponse<>(employeeList);
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
