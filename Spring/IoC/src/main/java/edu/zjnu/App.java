package edu.zjnu;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Main
 *
 * @Date 2024-07-26 下午3:18
 * @Author 杨海波
 **/
public class App {

    public static void main(String[] args) {

        testFileSystemXmlApplicationContext();

        testClassPathXmlApplicationContext();
    }

    private static void testFileSystemXmlApplicationContext() {
        // 加载基于文件系统的 XML 配置文件
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
        // 不支持绝对路径
        // ApplicationContext context = new FileSystemXmlApplicationContext("/Users/setsunayang/Documents/GitHub/base/Spring/IoC/doc/applicationContext.xml");
        HelloService helloService = (HelloService) context.getBean("helloService");
        String greeting = helloService.greet("World");
        System.out.println(greeting);
    }

    private static void testClassPathXmlApplicationContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloService helloService = (HelloService) context.getBean("helloService");
        String greeting = helloService.greet("World");
        System.out.println(greeting);
    }
}