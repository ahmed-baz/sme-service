package com.demo.user.controller;

import com.demo.user.model.Employee;
import com.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public Employee findUser(@PathVariable Long id) {
        return userService.findEmployeeById(id);
    }

}
