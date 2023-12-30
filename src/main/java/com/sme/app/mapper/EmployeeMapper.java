package com.sme.app.mapper;


import com.sme.app.entity.Employee;
import com.sme.app.vo.EmployeeVo;
import org.mapstruct.Mapper;


@Mapper
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeVo> {

}
