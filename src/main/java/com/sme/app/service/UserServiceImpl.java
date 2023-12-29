package com.sme.app.service;

import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.model.User;
import com.sme.app.repo.UserRepo;
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

    @Override
    public User addUser(User user) {
        user.setActive(false);
        return userRepo.save(user);
    }

}
