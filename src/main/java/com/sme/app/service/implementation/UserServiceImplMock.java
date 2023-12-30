package com.sme.app.service.implementation;

import com.sme.app.model.Employee;
import com.sme.app.model.User;
import com.sme.app.enums.UserRole;
import com.sme.app.utils.EmployeeUtil;
import com.sme.app.service.UserService;
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
        return new User(id, name, employee.getEmail(), UserRole.USER, true);
    }

    @Override
    public User addUser(User user) {
        user.setId(1655L);
        return user;
    }

}
