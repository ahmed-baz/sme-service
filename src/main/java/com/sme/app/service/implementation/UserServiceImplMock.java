package com.sme.app.service.implementation;

import com.sme.app.enums.UserRole;
import com.sme.app.service.UserService;
import com.sme.app.utils.EmployeeUtil;
import com.sme.app.vo.employee.EmployeeVo;
import com.sme.app.vo.UserVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserVo> findAll() {
        List<EmployeeVo> employeeList = EmployeeUtil.getEmployeeList(5);
        List<UserVo> userVoList = employeeList.stream()
                .map(emp -> UserVo.builder().name(emp.getFirstName() + " " + emp.getLastName())
                        .email(emp.getEmail())
                        .role(UserRole.CHECKER)
                        .active(true)
                        .build())
                .collect(Collectors.toList());
        return userVoList;
    }

    @Override
    public UserVo addUser(String smeCode, UserVo user) {
        user.setId(1655L);
        return user;
    }

    @Override
    public UserVo updateUser(Long id, UserVo user) {
        user.setId(1655L);
        return user;
    }

    @Override
    public Long deleteUser(Long id) {
        return id;
    }

}
