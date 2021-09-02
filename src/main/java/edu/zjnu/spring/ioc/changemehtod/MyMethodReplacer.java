package edu.zjnu.spring.ioc.changemehtod;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * @description: MyMethodReplacer
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class MyMethodReplacer implements MethodReplacer {


    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("has changed");
        return null;
    }
}
