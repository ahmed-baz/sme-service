package com.sme.app.permission;

import com.sme.app.entity.Sme;
import com.sme.app.entity.User;
import com.sme.app.enums.UserRole;
import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.interceptor.UserContext;
import com.sme.app.service.SmeService;
import com.sme.app.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AdminRoleAspect {

    private final String CREATE_ADMIN = "createAdmin";
    private final String CREATE_USER = "createUser";
    private final String CREATE_EMPLOYEE = "createEmployee";

    @Before("@annotation(com.sme.app.permission.annotations.SuperAdminOnly)")
    public void validateIsSuperAdmin(JoinPoint joinPoint) {
        if (!UserContext.isSuperAdmin()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        }
        String serviceName = joinPoint.getSignature().getName();
        if (CREATE_ADMIN.equals(serviceName)) {
            UserVo userVo = (UserVo) joinPoint.getArgs()[1];
            userVo.setRole(UserRole.ADMIN);
        }
    }

    @Before("@annotation(com.sme.app.permission.annotations.AdminOnly)")
    public void validateIsAdmin(JoinPoint joinPoint) {
        if (!UserContext.isAdmin()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        }
        String serviceName = joinPoint.getSignature().getName();
        if (CREATE_USER.equals(serviceName)) {
            String smeCode = (String) joinPoint.getArgs()[0];
            validateAdminSME(smeCode);
        }
    }

    private void validateAdminSME(String smeCode) {
        Sme sme = UserContext.getUser().getSme();
        if (null != sme && !smeCode.equals(sme.getCode())) {
            throw new AppExceptionResponse(AppErrorKeys.SME_ACCESS_NOT_ALLOWED_WITH_ADMIN, HttpStatus.FORBIDDEN);
        }
    }

    @Before("@annotation(com.sme.app.permission.annotations.MakerOnly)")
    public void validateIsMaker(JoinPoint joinPoint) {
        if (!UserContext.isMaker()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        }
        String serviceName = joinPoint.getSignature().getName();
        if (CREATE_EMPLOYEE.equals(serviceName)) {
            String smeCode = (String) joinPoint.getArgs()[0];
            validateAdminSME(smeCode);
        }
    }

    @Before("@annotation(com.sme.app.permission.annotations.CheckerOnly)")
    public void validateIsChecker() {
        if (!UserContext.isChecker()) {
            throw new AppExceptionResponse(AppErrorKeys.USER_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        }
    }

}