package com.sme.app.logging;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class ExecutionTimeLoggerAspect {

    @SneakyThrows
    @Around("@annotation(com.sme.app.logging.annotation.ExecutionTimeLogger)")
    public Object executionTimeLogger(ProceedingJoinPoint joinPoint) {
        try {
            long startTime = System.currentTimeMillis();
            Object proceed = joinPoint.proceed();
            long executionTime = (System.currentTimeMillis() - startTime);
            log.info("{} method was executed in {} milliseconds", joinPoint.getSignature(), executionTime);
            return proceed;
        } catch (Throwable e) {
            log.error("There was an error while calculating method execution time for {}", joinPoint.getSignature(), e);
            return joinPoint.proceed();
        }
    }
}

