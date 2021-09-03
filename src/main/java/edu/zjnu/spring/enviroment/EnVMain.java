package edu.zjnu.spring.enviroment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @description: EnVMain
 * @author: 杨海波
 * @date: 2021-09-03
 **/
public class EnVMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-enviroment.xml");
        EnvironmentHelper helper = (EnvironmentHelper) context.getBean("environmentHelper");

        Environment environment = helper.getEnvironment();
        System.out.println(environment.toString());

    }
}
