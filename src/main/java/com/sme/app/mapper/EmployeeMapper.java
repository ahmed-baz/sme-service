package com.sme.app.mapper;


import com.sme.app.entity.employee.Employee;
import com.sme.app.vo.employee.EmployeeVo;
import org.mapstruct.Mapper;


@Mapper
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeVo> {

}
