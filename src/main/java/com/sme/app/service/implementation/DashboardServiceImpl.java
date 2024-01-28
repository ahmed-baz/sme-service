package com.sme.app.service.implementation;

import com.sme.app.entity.employee.EmployeeSalaryMV;
import com.sme.app.entity.employee.EmployeeSalaryView;
import com.sme.app.repo.employee.EmployeeSalaryMVRepo;
import com.sme.app.repo.employee.EmployeeSalaryViewRepo;
import com.sme.app.service.DashboardService;
import com.sme.app.vo.employee.EmployeeSalaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final EmployeeSalaryViewRepo employeeSalaryViewRepo;
    private final EmployeeSalaryMVRepo employeeSalaryMVRepo;

    @Override
    public List<EmployeeSalaryVo> getSalariesCountMV() {
        List<EmployeeSalaryMV> all = employeeSalaryMVRepo.findAll();
        List<EmployeeSalaryVo> list = all.stream().map(data -> EmployeeSalaryVo.builder().code(data.getCode()).count(data.getCount()).sum(data.getSum()).build()).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<EmployeeSalaryVo> getSalariesCount() {
        List<EmployeeSalaryView> all = employeeSalaryViewRepo.findAll();
        List<EmployeeSalaryVo> list = all.stream().map(data -> EmployeeSalaryVo.builder().code(data.getCode()).count(data.getCount()).sum(data.getSum()).build()).collect(Collectors.toList());
        return list;
    }

    @Override
    public void refreshView() {
        employeeSalaryMVRepo.refreshView();
    }

}
