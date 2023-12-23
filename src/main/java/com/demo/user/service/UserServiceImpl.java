package com.demo.user.service;

import com.demo.user.exception.AppErrorKeys;
import com.demo.user.exception.AppExceptionResponse;
import com.demo.user.model.User;
import com.demo.user.repo.UserRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter
@Getter
@RequiredArgsConstructor
@ConditionalOnProperty(value = "employee.service.mock", havingValue = "false", matchIfMissing = true)
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public User findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        return user.get();
    }
}
