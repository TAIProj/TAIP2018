package com.waze.aspects;

import com.waze.domain.WazeAlert;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Configurable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.annotation.Annotation;

@Aspect
@Component
@Configurable
public aspect AdressAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.waze.service.WazeRouteService*(..)) || execution(* com.waze.service.WazeNotificationService*(..))")
    public void serviceClassMethods() {
    }

    @Pointcut("@annotation(com.waze.annotations.Loggable)")
    public void loggableAnnotationMethod() {
    }

    @Before("serviceClassMethods()")
    public void methodHadBeenExecuted(JoinPoint joinPoint) {
        System.out.println("Method from service has been executed");
        System.out.println(joinPoint.getSignature());
    }
    @Around("serviceClassMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        System.out.println(String.valueOf(System.currentTimeMillis() - startTime));
        return proceed;
    }

    @Around("loggableAnnotationMethod()")
    public void getLoggableMethods(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Annotation[] annotations = WazeAlert.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
    }

    @AfterReturning(value = "execution (* com.waze.service.WazeRouteService.getAddress())" , returning = "retVal")
    public void afterReturning(Object retVal) {
        System.out.println("It has returned " + retVal);
    }


}
