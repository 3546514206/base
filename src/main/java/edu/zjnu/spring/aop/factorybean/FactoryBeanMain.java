package edu.zjnu.spring.aop.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: FactoryBeanMain
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class FactoryBeanMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-aop-factorybean.xml");
        Car car = (Car) context.getBean("carFactoryBean");
        System.out.println(car.getClass());
        CarFactoryBean carFactoryBean = (CarFactoryBean) context.getBean("&carFactoryBean");
        System.out.println(carFactoryBean.getClass());
    }
}
