package aop;

import gudusoft.gsqlparser.EDbVendor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import demos.antiSQLInjection.*;

@Component
@Aspect
public class QueryValidatorAspect {

    private final Log log = LogFactory.getLog(this.getClass());

    @Before("execution(* com.*.Statement.execute(..))")
    public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        String query = (String) args[0];

        if (!new TAntiSQLInjection(EDbVendor.dbvmysql).isInjected(query)) {
            return joinPoint.proceed();
        } else {
            log.error("Infected query detected when calling tourist attractions service "
                    + joinPoint.getSignature()
                    + " with query "
                    + query);
        }

        return null;
    }

}
