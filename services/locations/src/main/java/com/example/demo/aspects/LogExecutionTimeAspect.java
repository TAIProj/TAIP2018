package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExecutionTimeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogExecutionTimeAspect.class);

    @Around("@annotation(com.example.demo.aspects.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        LOGGER.info(String.format("%s executed in %s ms", joinPoint.getSignature(), executionTime));
        return proceed;
    }

}
