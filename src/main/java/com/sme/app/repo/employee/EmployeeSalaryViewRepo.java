package com.sme.app.repo.employee;


import com.sme.app.entity.employee.EmployeeSalaryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface EmployeeSalaryViewRepo extends JpaRepository<EmployeeSalaryView, BigDecimal> {

}
