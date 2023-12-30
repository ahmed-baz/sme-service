package com.sme.app.integration.client;


import com.sme.app.vo.payload.AppResponse;
import com.sme.app.integration.model.EmployeeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "employee-service", url = "${emp.service.base.url}", path = "employee")
public interface EmployeeClient {

    @GetMapping("/{id}")
    AppResponse<EmployeeVO> findEmployeeById(@PathVariable Long id);

}
