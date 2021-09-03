package edu.zjnu.spring.ioc.changemehtod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: MethodReplacerMain
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class MethodReplacerMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-replacemethod.xml");
        TestChangeMethod changeMethod = context.getBean(TestChangeMethod.class);
        changeMethod.change();

    }
}
