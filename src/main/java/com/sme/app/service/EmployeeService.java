package com.sme.app.service;

import com.sme.app.integration.model.EmployeeVO;
import com.sme.app.vo.EmployeeSalaryVo;
import com.sme.app.vo.EmployeeVo;

import java.util.List;

public interface EmployeeService {

    void createEmployeeListAsync(int userNo);

    EmployeeVo findById(Long id);

    EmployeeVO findEmpById(Long id);

    EmployeeVO findEmployeeById(Long id);

    List<EmployeeSalaryVo> getEmployeesSalariesCount();

    void refreshView();

}
