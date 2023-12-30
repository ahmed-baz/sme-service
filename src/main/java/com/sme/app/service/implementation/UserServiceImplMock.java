package com.sme.app.service.implementation;

import com.sme.app.enums.UserRole;
import com.sme.app.service.UserService;
import com.sme.app.utils.EmployeeUtil;
import com.sme.app.vo.EmployeeVo;
import com.sme.app.vo.UserVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@ConditionalOnProperty(value = "employee.service.mock", havingValue = "true")
public class UserServiceImplMock implements UserService {

    public UserVo findById(Long id) {
        EmployeeVo employee = EmployeeUtil.createRandomEmployee();
        String name = employee.getFirstName() + " " + employee.getLastName();
        return new UserVo(name, employee.getEmail(), UserRole.USER, true);
    }

    @Override
    public UserVo addUser(UserVo user) {
        user.setId(1655L);
        return user;
    }

}
