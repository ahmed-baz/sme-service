package com.sme.app.service;

import com.sme.app.vo.UserVo;

public interface UserService {

    UserVo findById(Long id);

    UserVo addUser(UserVo user);

}
