package com.sme.app.graphql.mutation;


import com.sme.app.entity.Sme;
import com.sme.app.entity.employee.Employee;
import com.sme.app.graphql.query.EmployeeQuery;
import com.sme.app.repo.BaseRepo;
import com.sme.app.repo.employee.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
@RequiredArgsConstructor
public class EmployeeMutation extends MutationServiceImpl<Employee> {

    private final EmployeeQuery employeeQuery;
    private final EmployeeRepo employeeRepo;


    public Employee createEmployee(String firstName, String lastName, String email, BigDecimal salary, Long smeId) {
        Employee employee = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .salary(salary)
                .sme(Sme.builder().id(smeId).build())
                .build();

        return create(employee);
    }

    public Employee updateEmployee(Long id, String firstName, String lastName, String email, BigDecimal salary, Long smeId) {
        Employee oldEmployee = employeeQuery.findById(id);
        Employee employee = Employee.builder()
                .id(id)
                .firstName(firstName != null ? firstName : oldEmployee.getFirstName())
                .lastName(lastName != null ? lastName : oldEmployee.getLastName())
                .email(email != null ? email : oldEmployee.getEmail())
                .salary(salary != null ? salary : oldEmployee.getSalary())
                .sme(smeId != null ? Sme.builder().id(smeId).build() : oldEmployee.getSme())
                .build();

        return update(id, employee);
    }

    public boolean deleteEmployee(Long id) {
        return delete(id);
    }

    @Override
    public BaseRepo<Employee> getRepo() {
        return employeeRepo;
    }

}
