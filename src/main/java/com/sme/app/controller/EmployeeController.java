package com.sme.app.controller;

import com.sme.app.integration.model.EmployeeVO;
import com.sme.app.entity.Employee;
import com.sme.app.permission.annotations.AdminOnly;
import com.sme.app.service.EmployeeService;
import com.sme.app.service.UserExecutorService;
import com.sme.app.vo.EmployeeSalaryVo;
import com.sme.app.vo.EmployeeVo;
import com.sme.app.vo.payload.AppResponse;
import com.sme.app.vo.payload.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserExecutorService userExecutorService;

    @PostMapping("/list/db/{size}")
    public AppResponse<PageResponse<EmployeeVo>> createEmployeeList(@PathVariable int size) {
        List<EmployeeVo> employeeList = userExecutorService.createEmployeeList(size);
        return new AppResponse<>(new PageResponse<>(employeeList));
    }

    @PostMapping("/list/async/{size}")
    public AppResponse<Void> createEmployeeListAsync(@PathVariable int size) {
        employeeService.createEmployeeListAsync(size);
        return new AppResponse<>();
    }

    @GetMapping("/{id}")
    public AppResponse<EmployeeVo> findEmployeeById(@PathVariable Long id) {
        return new AppResponse<>(employeeService.findById(id));
    }

    @GetMapping("/find/{id}")
    public AppResponse<EmployeeVO> findEmployee(@PathVariable Long id) {
        return new AppResponse<>(employeeService.findEmpById(id));
    }

    @AdminOnly
    @GetMapping("/salary")
    public AppResponse<List<EmployeeSalaryVo>> getEmployeesSalariesCount() {
        return new AppResponse<>(employeeService.getEmployeesSalariesCount());
    }

    @AdminOnly
    @GetMapping("/refresh-view")
    public AppResponse<Void> refreshView() {
        employeeService.refreshView();
        return new AppResponse<>();
    }

}
