package com.example.weather.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class TaskExecutionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskExecutionUtil.class);

    @SafeVarargs
    public static <T> T execute(Task<T> task,
                                int noOfRetryAttempts,
                                long sleepInterval,
                                Class<? extends Throwable>... ignoreExceptions)
    {

        if (noOfRetryAttempts < 1) {
            noOfRetryAttempts = 1;
        }
        Set<Class<? extends Throwable>> ignoreExceptionsSet = new HashSet<Class<? extends Throwable>>();
        if (ignoreExceptions != null && ignoreExceptions.length > 0) {
            for (Class<? extends Throwable> ignoreException : ignoreExceptions) {
                ignoreExceptionsSet.add(ignoreException);
            }
        }

        LOGGER.debug("noOfRetryAttempts = "+noOfRetryAttempts);
        LOGGER.debug("ignoreExceptionsSet = "+ignoreExceptionsSet);

        T result = null;
        for (int retryCount = 1; retryCount <= noOfRetryAttempts; retryCount++) {
            LOGGER.debug("Executing the task. Attemp#"+retryCount);
            try {
                result = task.execute();
                break;
            } catch (RuntimeException t) {
                Throwable e = t.getCause();
                LOGGER.error(" Caught Exception class"+e.getClass());
                for (Class<? extends Throwable> ignoreExceptionClazz : ignoreExceptionsSet) {
                    LOGGER.error(" Comparing with Ignorable Exception : "+ignoreExceptionClazz.getName());

                    if (!ignoreExceptionClazz.isAssignableFrom(e.getClass())) {
                        LOGGER.error("Encountered exception which is not ignorable: "+e.getClass());
                        LOGGER.error("Throwing exception to the caller");

                        throw t;
                    }
                }
                LOGGER.error("Failed at Retry attempt :" + retryCount + " of : " + noOfRetryAttempts);
                if (retryCount >= noOfRetryAttempts) {
                    LOGGER.error("Maximum retrial attempts exceeded.");
                    LOGGER.error("Throwing exception to the caller");
                    throw t;
                }
                try {
                    Thread.sleep(sleepInterval);
                } catch (InterruptedException e1) {
                    //Intentionally left blank
                }
            }
        }
        return result;
    }
}
