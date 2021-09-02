package edu.zjnu.spring.ioc.lookup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: LookUpMain
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class LookUpMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc-lookup.xml");
        GetBeanTest getBean = context.getBean(GetBeanTest.class);
        getBean.showMe();
    }
}
