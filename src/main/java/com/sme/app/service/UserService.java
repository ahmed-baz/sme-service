package com.sme.app.service;

import com.sme.app.entity.User;

public interface UserService {

    User findById(Long id);

    User addUser(User user);

}
