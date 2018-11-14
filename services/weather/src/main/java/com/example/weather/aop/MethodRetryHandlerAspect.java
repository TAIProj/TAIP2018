package com.example.weather.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class MethodRetryHandlerAspect {

    @Around("@annotation(com.example.weather.aop.Retry)")
    public Object audit(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        result = retryableExecute(proceedingJoinPoint);
        return result;
    }

    protected Object retryableExecute(final ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        Retry retry = method.getDeclaredAnnotation(Retry.class);
        int retryAttempts = retry.retryAttempts();
        long sleepInterval = retry.sleepInterval();
        Class<? extends Throwable>[] ignoreExceptions = retry.ignoreExceptions();

        Task<Object> task = new Task<Object>() {
            @Override
            public Object execute() {
                try {
                    return proceedingJoinPoint.proceed();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        };

        return TaskExecutionUtil.execute(task, retryAttempts, sleepInterval, ignoreExceptions);

    }
}
