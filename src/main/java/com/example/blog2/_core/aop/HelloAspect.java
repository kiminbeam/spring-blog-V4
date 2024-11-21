package com.example.blog2._core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HelloAspect {

    //Aspect 만들어서 getMapping에 실행되게 만들어보기
    //매개변수에 Integer가 있으면 [매개변수] 번아 안녕 출력되게 하기

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void checkGetMapping(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Integer) {
                System.out.println(arg + "번아 안녕");
            }
        }
    }
}
