package com.sme.app.service;

import com.sme.app.vo.employee.EmployeeVo;

import java.util.List;


public interface EmployeeExecutorService {

    List<EmployeeVo> createEmployeeList(int size);

    void createEmployeeListAsync(int userNo);

}
