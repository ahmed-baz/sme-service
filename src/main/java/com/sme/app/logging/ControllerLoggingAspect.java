package com.sme.app.logging;

import com.sme.app.permission.UserContext;
import com.sme.app.vo.UserVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class ControllerLoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "execution(* com.sme.app.controller.UserController.*(..))")
    private void doLogUserController() {
    }

    @Pointcut(value = "execution(* com.sme.app.controller.UserController.set*(..))")
    private void setters() {
    }

    @Pointcut(value = "execution(* com.sme.app.controller.UserController.get*(..))")
    private void getters() {
    }

    @Pointcut("doLogUserController() && ! (setters() ||getters())")
    private void doLogUserControllerIgnoring() {
    }

    @Before(value = "execution(public com.sme.app.vo.UserVo addUser(com.sme.app.vo.UserVo))")
    public void logAddUserAction(JoinPoint joinPoint) {
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String name = joinPoint.getSignature().getName();
        UserVo userVo = (UserVo) (joinPoint.getArgs()[0]);
        log.info(String.format("admin %s try to add admin user with email %s", UserContext.getEmail(), userVo.getEmail()));
    }

    @Before("doLogUserControllerIgnoring()")
    public void logUserAction(JoinPoint joinPoint) {
        log.info(String.format("admin %s try to add/update/delete [%s] admin user", UserContext.getEmail(), joinPoint.getSignature().getName()));
    }

}