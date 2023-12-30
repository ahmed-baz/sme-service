package com.sme.app.controller;

import com.sme.app.entity.User;
import com.sme.app.permission.annotations.AdminOnly;
import com.sme.app.permission.annotations.MakerOnly;
import com.sme.app.service.UserService;
import com.sme.app.vo.payload.AppResponse;
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
