package com.sme.app.repo.employee;


import com.sme.app.entity.employee.Employee;
import com.sme.app.repo.BaseRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends BaseRepo<Employee> {

    Optional<Employee> findEmployeeByEmail(String email);

}
