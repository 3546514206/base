package edu.zjnu.spring.aop;

import edu.zjnu.spring.aop.aopconfig.Swimable;
import edu.zjnu.spring.aop.beanconfig.Sleepable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: AoPLoader
 * @author: 杨海波
 * @date: 2021-08-19
 **/
public class AoPLoader {
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

    /**
     * 通过<aop:config/>标签
     */
    private static void aopConfig() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-aop-conf.xml");
        Swimable duck = (Swimable) context.getBean("duck");
        duck.swim();
    }

    public static void main(String[] args) {
        aopBean();
    }

}
