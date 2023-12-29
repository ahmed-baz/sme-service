package com.demo.user.controller;

import com.demo.user.model.User;
import com.demo.user.permission.annotations.AdminOnly;
import com.demo.user.permission.annotations.MakerOnly;
import com.demo.user.service.UserService;
import com.demo.user.vo.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @AdminOnly
    @GetMapping("/{id}")
    public AppResponse<User> findUser(@PathVariable Long id) {
        return new AppResponse<>(userService.findById(id));
    }

    @MakerOnly
    @PostMapping
    public AppResponse<User> addUser(@RequestBody User user) {
        return new AppResponse<>(userService.addUser(user));
    }

}
