package edu.zjnu.spring.aop.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: AspectMain
 * @author: 杨海波
 * @date: 2021-09-04
 **/
public class AspectMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aspect.xml");
        TestBean bean = (TestBean) context.getBean("testBean");
        bean.test();
    }
}
