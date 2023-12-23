package com.demo.user.service;

import com.demo.user.model.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@ConditionalOnProperty(value = "employee.service.mock", havingValue = "true")
public class UserServiceImplMock implements UserService {

    public Employee findEmployeeById(Long id) {
        return EmployeeUtil.createRandomEmployee();
    }

}
