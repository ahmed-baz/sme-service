package com.sme.app.logging;

import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.interceptor.UserContext;
import com.sme.app.vo.SmeVo;
import com.sme.app.vo.UserVo;
import com.sme.app.vo.employee.EmployeeVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Order(3)
@Component
public class LoggingAspect {

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

    @AfterReturning(value = "execution(public * com.sme.app.service.SmeService.findAllSmes())", returning = "smeVos")
    public void testAfterReturning(JoinPoint joinPoint, List<SmeVo> smeVos) {
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String name = joinPoint.getSignature().getName();
        log.info(String.format("smes size %s", smeVos.size()));
    }

    @AfterThrowing(value = "execution(public * com.sme.app.controller.UserController.createAdmin(..))", throwing = "exception")
    public void testAfterThrowing(JoinPoint joinPoint, Exception exception) {
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String name = joinPoint.getSignature().getName();
        String smeCode = (String) (joinPoint.getArgs()[0]);
        if (exception instanceof AppExceptionResponse) {
            AppExceptionResponse response = (AppExceptionResponse) exception;
            log.info(String.format("failed to create admin for SME %s because of %s", smeCode, response.getErrorKey()));
        }
    }

    @After(value = "execution(public com.sme.app.vo.employee.EmployeeVo com.sme.app.service.EmployeeService.createEmployee(..))")
    public void testAfter(JoinPoint joinPoint) {
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String name = joinPoint.getSignature().getName();
        String smeCode = (String) (joinPoint.getArgs()[0]);
        EmployeeVo employee = (EmployeeVo) (joinPoint.getArgs()[1]);
        log.info(String.format("new employee with email %s  has  been created into SME %s ", employee.getEmail(), smeCode));
    }

}