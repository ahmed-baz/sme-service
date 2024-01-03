package com.sme.app.graphql.query;


import com.sme.app.entity.employee.Employee;
import com.sme.app.repo.BaseRepo;
import com.sme.app.repo.employee.EmployeeRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeQuery extends QueryServiceImpl<Employee> {

    private final EmployeeRepo employeeRepo;

    public Iterable<Employee> findAllEmployees() {
        return findAll();
    }

    public Employee findEmployeeById(Long id) {
        return findById(id);
    }

    public Long countEmployees() {
        return count();
    }

    @Override
    public BaseRepo<Employee> getRepo() {
        return employeeRepo;
    }
}
