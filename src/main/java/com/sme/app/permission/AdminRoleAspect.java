package com.sme.app.permission;

import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AdminRoleAspect {

    @Before("@annotation(com.sme.app.permission.annotations.AdminOnly)")
    public void validateIsAdmin() {
        if (!UserContext.isAdmin()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        }
    }

    @Before("@annotation(com.sme.app.permission.annotations.MakerOnly)")
    public void validateIsMaker() {
        if (!UserContext.isMaker()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        }
    }

    @Before("@annotation(com.sme.app.permission.annotations.CheckerOnly)")
    public void validateIsChecker() {
        if (!UserContext.isChecker()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        }
    }

}