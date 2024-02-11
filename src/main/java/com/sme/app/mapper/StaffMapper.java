package com.sme.app.mapper;


import com.sme.app.proto.Employee;
import com.sme.app.vo.employee.EmployeeVo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StaffMapper {

    EmployeeVo employeeToEmployeeVO(Employee employee);
}
