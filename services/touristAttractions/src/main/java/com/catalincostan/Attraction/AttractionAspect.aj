package com.catalincostan.Attraction;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Configurable
public aspect AttractionAspect{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.catalincostan.Attraction.AttractionService*(..)) || execution(* com.catalincostan.Attraction.AttractionRepository*(..))")
    public void serviceClassMethods(){
    }

    @Pointcut("@annotation(com.catalincostan.Attraction.annotations.Loggable)")
    public void loggableAnnotationMethod(){
    }

    @Before("serviceClassMethods()")
    public void methodHadBeenExecuted(JoinPoint joinPoint){
        System.out.println("Method from service has been executed");
        System.out.println(joinPoint.getSignature());
    }

    @Around("serviceClassMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp)throws Throwable{
        Long startTime=System.currentTimeMillis();
        Object proceed=pjp.proceed();
        System.out.println(String.valueOf(System.currentTimeMillis()-startTime));
        return proceed;
    }

    @AfterReturning(value = "execution (* com.catalincostan.Attraction.AttractionService.getById())", returning = "retVal")
    public void afterReturning(Object retVal){
        System.out.println("It has returned " + retVal);
    }
}
