package com.sme.app.repo;


import com.sme.app.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Repository
public interface EmployeeSalaryRepo extends JpaRepository<EmployeeSalary, BigDecimal> {

    @Transactional
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW EMPLOYEE_SALARY", nativeQuery = true)
    void refreshView();

}
