package edu.zjnu.spring.aop.aopconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class AopConfigMain {

    public static void main(String[] args) {
        aopConfig();
    }

    /**
     * 通过<aop:config/>标签
     */
    private static void aopConfig() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-aop-conf.xml");
        Swimable duck = (Swimable) context.getBean("duck");
        duck.swim();
    }
}
