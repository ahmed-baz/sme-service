package com.sme.app.controller;

import com.sme.app.permission.annotations.AdminOnly;
import com.sme.app.permission.annotations.MakerOnly;
import com.sme.app.service.UserService;
import com.sme.app.vo.UserVo;
import com.sme.app.vo.payload.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @AdminOnly
    @GetMapping("/{id}")
    public AppResponse<UserVo> findUser(@PathVariable Long id) {
        return new AppResponse<>(userService.findById(id));
    }

    @AdminOnly
    @GetMapping("/all")
    public AppResponse<List<UserVo>> findAll() {
        return new AppResponse<>(userService.findAll());
    }

    @MakerOnly
    @PostMapping
    public AppResponse<UserVo> addUser(@RequestBody UserVo user) {
        return new AppResponse<>(userService.addUser(user));
    }

    @AdminOnly
    @PutMapping("/{id}")
    public AppResponse<UserVo> updateUser(@PathVariable Long id, @RequestBody UserVo user) {
        return new AppResponse<>(userService.updateUser(id, user));
    }

    @AdminOnly
    @DeleteMapping("/{id}")
    public AppResponse<Long> deleteUser(@PathVariable Long id) {
        return new AppResponse<>(userService.deleteUser(id));
    }

}
