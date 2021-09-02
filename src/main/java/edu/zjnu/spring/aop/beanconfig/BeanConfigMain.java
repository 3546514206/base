package edu.zjnu.spring.aop.beanconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class BeanConfigMain {

    /**
     * 通过<bean/>标签配合ProxyFactoryBean的方式
     */
    private static void aopBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-bean-conf.xml");

        Sleepable me = (Sleepable) context.getBean("me");
        Sleepable meProxy = (Sleepable) context.getBean("meProxy");
        System.out.println("没有代理时：");
        me.sleep();

        System.out.println();
        System.out.println("AOP代之后：");
        meProxy.sleep();
    }

    public static void main(String[] args) {
        aopBean();
    }
}
