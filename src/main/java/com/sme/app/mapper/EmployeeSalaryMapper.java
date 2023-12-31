package com.sme.app.mapper;


import com.sme.app.entity.EmployeeSalary;
import com.sme.app.vo.EmployeeSalaryVo;
import org.mapstruct.Mapper;


@Mapper
public interface EmployeeSalaryMapper extends BaseMapper<EmployeeSalary, EmployeeSalaryVo> {

}
