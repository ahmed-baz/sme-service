package com.demo.user.permission;

import com.demo.user.exception.AppErrorKeys;
import com.demo.user.exception.AppExceptionResponse;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AdminRoleAspect {

    @Before("@annotation(com.demo.user.permission.annotations.AdminOnly)")
    public void validateIsAdmin() {
        if (!UserContext.isAdmin()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        }
    }

}