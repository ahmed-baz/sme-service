package com.sme.app.controller;

import com.sme.app.permission.annotations.AdminOnly;
import com.sme.app.permission.annotations.SuperAdminOnly;
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

    @GetMapping("/{id}")
    public AppResponse<UserVo> findUser(@PathVariable Long id) {
        return new AppResponse<>(userService.findById(id));
    }

    @AdminOnly
    @GetMapping("/all")
    public AppResponse<List<UserVo>> findAll() {
        return new AppResponse<>(userService.findAll());
    }

    @SuperAdminOnly
    @PostMapping("/admin/{smeCode}")
    public AppResponse<UserVo> createAdmin(@PathVariable String smeCode, @RequestBody UserVo user) {
        return new AppResponse<>(userService.addUser(smeCode, user));
    }

    @AdminOnly
    @PostMapping("/{smeCode}")
    public AppResponse<UserVo> createUser(@PathVariable String smeCode, @RequestBody UserVo user) {
        return new AppResponse<>(userService.addUser(smeCode, user));
    }

    @SuperAdminOnly
    @PutMapping("/admin/{id}")
    public AppResponse<UserVo> updateAdmin(@PathVariable Long id, @RequestBody UserVo user) {
        return new AppResponse<>(userService.updateUser(id, user));
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
