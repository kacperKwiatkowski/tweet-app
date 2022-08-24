package com.kacperKwiatkowski.tweetApp.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectLogger {

    @Before("execution(* com.kacperKwiatkowski.tweetApp..*(..))") // This is the Advice
    public void logTweetMethodsBeforeInvoke(JoinPoint jp) {
        log.info("Executing before => Method " + mapClassAndMethod(jp.getSignature().toString()) + " with arguments: " + mapArgs(jp.getArgs()));
    }

    @AfterReturning("execution(* com.kacperKwiatkowski.tweetApp..*(..))") // This is the Advice
    public void logTweetMethodsAfterInvoke(JoinPoint jp) {
        log.info("Executing after => Method " + mapClassAndMethod(jp.getSignature().toString()) + " with arguments: " + mapArgs(jp.getArgs()));
    }

    private String mapClassAndMethod(String classPath) {
        String[] elements = classPath.split("\\.");

        if (elements.length > 1) {
            return elements[elements.length - 2] + "::" + elements[elements.length - 1];
        }

        return classPath;
    }

    private String mapArgs(Object[] args) {
        StringBuilder sb = new StringBuilder();

        for (Object o : args) {
            sb.append(o.toString());
            sb.append(" ");
        }

        return sb.toString();
    }
}
