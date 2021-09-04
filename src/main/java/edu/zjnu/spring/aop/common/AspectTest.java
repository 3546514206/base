package edu.zjnu.spring.aop.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @description: AspectTest
 * @author: 杨海波
 * @date: 2021-09-04
 **/
@Aspect
public class AspectTest {

    @Pointcut("execution(* *.test(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("before");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("after");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint) {

        System.out.println("around tag one");

        Object o = null;

        try {
            o = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("around tag two");

        return o;
    }

}
