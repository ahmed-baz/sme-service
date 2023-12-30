package com.sme.app.service;

import com.sme.app.entity.Employee;

import java.util.List;


public interface UserExecutorService {

    List<Employee> createEmployeeList(int size);

}
