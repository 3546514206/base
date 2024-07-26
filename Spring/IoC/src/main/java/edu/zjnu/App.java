package edu.zjnu;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Main
 *
 * @Date 2024-07-26 下午3:18
 * @Author 杨海波
 **/
public class App {

    public static void main(String[] args) {
        // Load Spring context from XML configuration file
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the greetingService bean
        GreetingService greetingService = (GreetingService) context.getBean("greetingService");

        // Use the greetingService
        String greeting = greetingService.greet("World");
        System.out.println(greeting);
    }
}