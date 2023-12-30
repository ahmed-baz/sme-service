package com.sme.app.service.implementation;

import com.sme.app.entity.Employee;
import com.sme.app.utils.EmployeeUtil;
import com.sme.app.vo.EmployeeVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.Callable;

@Setter
@Getter
public class DummyEmployeeService implements Callable<List<EmployeeVo>> {

    private int size;

    @Override
    public List<EmployeeVo> call() throws Exception {
        return EmployeeUtil.getEmployeeList(size, 1);
    }

}
