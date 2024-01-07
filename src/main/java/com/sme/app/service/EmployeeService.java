package com.sme.app.service;

import com.sme.app.integration.model.EmployeeVO;
import com.sme.app.vo.employee.EmployeeSalaryVo;
import com.sme.app.vo.employee.EmployeeVo;

import java.util.List;

public interface EmployeeService {

    void createEmployeeListAsync(int userNo);

    EmployeeVo findById(Long id);

    EmployeeVo findByEmail(String email);

    EmployeeVo createEmployee(String smeCode, EmployeeVo employeeVo);

    EmployeeVO findEmpById(Long id);

    boolean deleteEmployee(Long id);

    EmployeeVO findEmployeeById(Long id);

    List<EmployeeSalaryVo> getSalariesCountMV();

    List<EmployeeSalaryVo> getSalariesCount();

    void refreshView();

}
