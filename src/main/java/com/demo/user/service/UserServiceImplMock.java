package com.demo.user.service;

import com.demo.user.model.Employee;
import com.demo.user.model.User;
import com.demo.user.vo.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@ConditionalOnProperty(value = "employee.service.mock", havingValue = "true")
public class UserServiceImplMock implements UserService {

    public User findById(Long id) {
        Employee employee = EmployeeUtil.createRandomEmployee();
        String name = employee.getFirstName() + " " + employee.getLastName();
        return new User(id, name, employee.getEmail(), UserRole.USER);
    }

}
