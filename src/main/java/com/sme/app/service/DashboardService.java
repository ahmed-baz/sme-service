package com.sme.app.service;

import com.sme.app.vo.employee.EmployeeSalaryVo;

import java.util.List;

public interface DashboardService {

    List<EmployeeSalaryVo> getSalariesCountMV();

    List<EmployeeSalaryVo> getSalariesCount();

    void refreshView();

}
