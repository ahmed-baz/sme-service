package com.demo.user.feign;


import com.demo.user.vo.AppResponse;
import com.demo.user.vo.EmployeeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "employee-service", url = "${emp.service.base.url}", path = "employee")
public interface EmployeeServiceClient {

    @GetMapping("/{id}")
    AppResponse<EmployeeVO> findEmployeeById(@PathVariable Long id);

}
