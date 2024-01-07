package com.sme.app.service;

import com.sme.app.vo.UserVo;

import java.util.List;

public interface UserService {

    UserVo findById(Long id);

    List<UserVo> findAll();

    UserVo addUser(String smeCode, UserVo user);

    UserVo updateUser(Long id, UserVo user);

    Long deleteUser(Long id);

}
