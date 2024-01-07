package com.sme.app.mapper;


import com.sme.app.entity.employee.Employee;
import com.sme.app.entity.employee.EmployeeView;
import com.sme.app.vo.employee.EmployeeVo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeVo> {

    EmployeeVo fromEmployeeViewToEmployeeVO(EmployeeView employeeView);
}
