package com.demo.user.feign;


import com.demo.user.vo.EmployeeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "employee-service", url = "http://localhost:9999", path = "employee")
public interface EmployeeProxy {

    @GetMapping("/findById/{id}")
    EmployeeVO findEmployeeById(@PathVariable int id);

}
