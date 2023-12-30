package com.sme.app.service.implementation;

import com.sme.app.entity.Employee;
import com.sme.app.utils.EmployeeUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.Callable;

@Setter
@Getter
public class DummyEmployeeService implements Callable<List<Employee>> {

    private int size;

    @Override
    public List<Employee> call() throws Exception {
        return EmployeeUtil.getEmployeeList(size, 1);
    }

}
