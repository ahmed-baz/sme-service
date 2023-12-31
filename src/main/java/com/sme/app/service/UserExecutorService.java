package com.sme.app.service;

import com.sme.app.vo.employee.EmployeeVo;

import java.util.List;


public interface UserExecutorService {

    List<EmployeeVo> createEmployeeList(int size);

}
