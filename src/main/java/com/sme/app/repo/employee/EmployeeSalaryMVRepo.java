package com.sme.app.repo.employee;


import com.sme.app.entity.employee.EmployeeSalaryMV;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSalaryMVRepo extends JpaRepository<EmployeeSalaryMV, String> {

    @Transactional
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW SME_EMPLOYEES_SALARY_MV", nativeQuery = true)
    void refreshView();

}
