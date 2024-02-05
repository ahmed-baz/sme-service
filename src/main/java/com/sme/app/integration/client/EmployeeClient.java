package com.sme.app.integration.client;


import com.sme.app.integration.model.EmployeeVO;
import com.sme.app.vo.payload.AppResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "employee-service", url = "${emp.service.base.url}", path = "employee")
public interface EmployeeClient {

    @GetMapping("/{id}")
    AppResponse<EmployeeVO> findEmployeeById(@PathVariable Long id);

    @GetMapping("/find/{email}")
    AppResponse<EmployeeVO> findByEmail(@PathVariable String email);

    @PostMapping("/list")
    AppResponse<List<EmployeeVO>> addEmployeeList(@RequestBody List<EmployeeVO> employees);

    @PostMapping
    AppResponse<EmployeeVO> addEmployee(@RequestBody EmployeeVO employeeVO);

}
