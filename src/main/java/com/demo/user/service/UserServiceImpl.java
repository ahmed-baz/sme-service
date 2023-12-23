package com.demo.user.service;

import com.demo.user.model.Employee;
import com.demo.user.repo.EmployeeRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@RequiredArgsConstructor
@ConditionalOnProperty(value = "employee.service.mock", havingValue = "false", matchIfMissing = true)
public class UserServiceImpl implements UserService{

    private final EmployeeRepo employeeRepo;

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }
}
