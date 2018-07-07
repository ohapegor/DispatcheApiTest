package ru.ohapegor.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MyAspect {

    @Around("@annotation(ru.ohapegor.test.InLines)")
    public void line(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("<=============================================>");
        joinPoint.proceed();
        System.out.println("<=============================================>");
    }
}
