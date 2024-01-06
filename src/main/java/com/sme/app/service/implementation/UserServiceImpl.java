package com.sme.app.service.implementation;

import com.sme.app.entity.Sme;
import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.entity.User;
import com.sme.app.mapper.UserMapper;
import com.sme.app.repo.UserRepo;
import com.sme.app.service.SmeService;
import com.sme.app.service.UserService;
import com.sme.app.vo.SmeVo;
import com.sme.app.vo.UserVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Setter
@Getter
@RequiredArgsConstructor
@Log4j2
@ConditionalOnProperty(value = "employee.service.mock", havingValue = "false", matchIfMissing = true)
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final SmeService smeService;
    private final UserMapper userMapper;

    public UserVo findById(Long id) {
        return userMapper.entityToVo(loadUserById(id));
    }

    private User loadUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
        return user.get();
    }

    @Override
    public List<UserVo> findAll() {
        List<User> all = userRepo.findAll();
        return userMapper.entityListToVoList(all);
    }

    @Override
    public UserVo addUser(String smeCode, UserVo userVo) {
        try {
            User user = userMapper.voToEntity(userVo);
            user.setActive(true);
            List<SmeVo> smes = smeService.findAllSmes();
            Optional<SmeVo> smeVo = smes.stream().filter(sme -> smeCode.equals(sme.getCode())).findFirst();
            if (smeVo.isPresent()) {
                user.setSme(new Sme(smeVo.get().getId()));
                User savedUser = userRepo.save(user);
                return userMapper.entityToVo(savedUser);
            } else {
                throw new AppExceptionResponse(AppErrorKeys.INVALID_SME, HttpStatus.BAD_REQUEST);
            }
        } catch (DataIntegrityViolationException ex) {
            log.error(ex);
            throw new AppExceptionResponse(AppErrorKeys.EXIST_EMAIL, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            log.error(ex);
            throw new AppExceptionResponse();
        }
    }

    @Override
    public UserVo updateUser(Long id, UserVo userVo) {
        try {
            User user = loadUserById(id);
            userMapper.updateEntityFromVo(userVo, user);
            user.setId(id);
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

    @Override
    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return id;
    }

}
