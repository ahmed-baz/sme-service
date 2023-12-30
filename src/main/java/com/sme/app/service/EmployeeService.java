package com.sme.app.service;

import com.sme.app.integration.model.EmployeeVO;
import com.sme.app.model.Employee;

public interface EmployeeService {

    void createEmployeeListAsync(int userNo);

    Employee findById(Long id);

    EmployeeVO findEmpById(Long id);

    EmployeeVO findEmployeeById(Long id);

}
