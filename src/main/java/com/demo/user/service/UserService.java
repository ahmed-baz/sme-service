package com.demo.user.service;

import com.demo.user.model.User;

public interface UserService {

    User findById(Long id);

    User addUser(User user);

}
