package com.sme.app.repo.employee;


import com.sme.app.entity.employee.EmployeeSalaryMV;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface EmployeeSalaryMVRepo extends JpaRepository<EmployeeSalaryMV, BigDecimal> {

    //@Transactional
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW EMPLOYEE_SALARY_MV", nativeQuery = true)
    void refreshView();

}
