package com.example.SpringAOP.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @After("execution(* com.example.SpringAOP.service.PersonService.*(..))")
    @Order(1)
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Executed: " + joinPoint.getSignature().getName());
        System.out.println("Executed: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.SpringAOP.service.PersonService.*(..))")
    @Order(2)
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() -start;
        System.out.println("Метод: "+joinPoint.getSignature().getName() + " - "+elapsedTime+" милсек");
        return result;
    }

}
