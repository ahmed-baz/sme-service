package com.sme.app.service.implementation;

import com.sme.app.mapper.StaffMapper;
import com.sme.app.proto.Employee;
import com.sme.app.proto.EmployeeServiceGrpc;
import com.sme.app.vo.employee.EmployeeVo;
import lombok.Getter;
import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@Getter
public class EmployeeServiceRPC extends EmployeeServiceGrpc.EmployeeServiceImplBase {

    @GrpcClient("staff-service")
    private EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeService;
    @Autowired
    private StaffMapper staffMapper;

    public EmployeeVo findById(Long id) {
        Employee employee = Employee.newBuilder().setId(Math.toIntExact(id)).build();
        Employee response = employeeService.getEmployeeById(employee);
        return staffMapper.employeeToEmployeeVO(response);
    }


    public List<EmployeeVo> findList() {
        return null;
    }


    public EmployeeVo findByEmail(String email) {
        return null;
    }


    public EmployeeVo createEmployee(String smeCode, EmployeeVo employeeVo) {
        return null;
    }


    public boolean deleteEmployee(Long id) {
        return false;
    }


    public EmployeeVo updateEmployee(EmployeeVo employeeVo) {
        return null;
    }
}
