package ru.ohapegor.test;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Aspect
@SpringBootApplication
public class TestApplication {

    @PostConstruct
    @InLines
    private void testAspect(){
        System.out.println("test aspect");
    }

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @Around("@annotation(ru.ohapegor.test.InLines)")
    public void line(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("<=============================================>");
        joinPoint.proceed();
        System.out.println("<=============================================>");
    }
}
