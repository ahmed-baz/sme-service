package com.sme.app.service.implementation;

import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.entity.User;
import com.sme.app.mapper.UserMapper;
import com.sme.app.repo.UserRepo;
import com.sme.app.service.UserService;
import com.sme.app.vo.UserVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter
@Getter
@RequiredArgsConstructor
@Log4j2
@ConditionalOnProperty(value = "employee.service.mock", havingValue = "false", matchIfMissing = true)
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserVo findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        return userMapper.entityToVo(user.get());
    }

    @Override
    public UserVo addUser(UserVo userVo) {
        try {
            User user = userMapper.voToEntity(userVo);
            user.setActive(false);
            User savedUser = userRepo.save(user);
            return userMapper.entityToVo(savedUser);
        } catch (DataIntegrityViolationException ex) {
            log.error(ex);
            throw new AppExceptionResponse(AppErrorKeys.EXIST_EMAIL, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error(ex);
            throw new AppExceptionResponse();
        }
    }

}
