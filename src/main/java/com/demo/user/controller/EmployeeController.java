package com.demo.user.controller;

import com.demo.user.model.Employee;
import com.demo.user.service.EmployeeService;
import com.demo.user.vo.EmployeeVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Setter
@Getter
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("employees")
    public void addEmployeeList() {
        getEmployeeService().addEmployeeList();
    }

    @PostMapping("employee/{id}")
    public Employee findEmployee(@PathVariable int id) {
        return getEmployeeService().findEmployeeById(id);
    }

    @PostMapping("report")
    public long validateEmployees() {
        return getEmployeeService().validateEmployees();
    }

}
