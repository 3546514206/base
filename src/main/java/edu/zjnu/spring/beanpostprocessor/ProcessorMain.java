package edu.zjnu.spring.beanpostprocessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: ProcessorMain
 * @author: 杨海波
 * @date: 2021-09-03
 **/
public class ProcessorMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beanpostprocessor.xml");
        ISampleService sampleService = (ISampleService) context.getBean("sampleService");
        sampleService.say();
    }
}
