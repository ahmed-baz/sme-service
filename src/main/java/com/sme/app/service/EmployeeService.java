package com.sme.app.service;

import com.sme.app.criteria.EmployeeCriteria;
import com.sme.app.integration.model.EmployeeVO;
import com.sme.app.vo.employee.EmployeeVo;
import com.sme.app.vo.payload.PageResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeVo findById(Long id);

    List<EmployeeVo> findList();

    PageResponse<EmployeeVo> search(EmployeeCriteria criteria);

    List<EmployeeVo> getEmployeeSalaryStatistics(EmployeeCriteria criteria);

    PageResponse<EmployeeVo> findListBySmeName(String name, int index, int size);

    EmployeeVo findByEmail(String email);

    EmployeeVo createEmployee(String smeCode, EmployeeVo employeeVo);

    EmployeeVo updateEmployee(EmployeeVo employeeVo);

    EmployeeVO findEmpById(Long id);

    boolean deleteEmployee(Long id);

    EmployeeVO findEmployeeById(Long id);

    EmployeeVo doDummyUpdate();

}
