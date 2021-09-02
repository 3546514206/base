package edu.zjnu.spring.ioc.cyclicdependencies.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: 构造器循环依赖是无法解决的，即便是Spring @see CyclicDependencies.java
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc-constructorcyclicdependencies.xml");
        //context.getBean("a");
    }
}
