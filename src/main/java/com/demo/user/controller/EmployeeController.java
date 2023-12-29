package com.demo.user.controller;

import com.demo.user.vo.AppResponse;
import com.demo.user.model.Employee;
import com.demo.user.service.EmployeeService;
import com.demo.user.service.UserExecutorService;
import com.demo.user.vo.EmployeeVO;
import com.demo.user.vo.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserExecutorService userExecutorService;

    @PostMapping("/list/db/{size}")
    public AppResponse<PageResponse<Employee>> createEmployeeList(@PathVariable int size) {
        List<Employee> employeeList = userExecutorService.createEmployeeList(size);
        return new AppResponse<>(new PageResponse<>(employeeList));
    }

    @PostMapping("/list/async/{size}")
    public AppResponse<Void> createEmployeeListAsync(@PathVariable int size) {
        employeeService.createEmployeeListAsync(size);
        return new AppResponse<>();
    }

    @GetMapping("/{id}")
    public AppResponse<Employee> findEmployeeById(@PathVariable Long id) {
        return new AppResponse<>(employeeService.findById(id));
    }

    @GetMapping("/find/{id}")
    public AppResponse<EmployeeVO> findEmployee(@PathVariable Long id) {
        return new AppResponse<>(employeeService.findEmployeeById(id));
    }

}
