package edu.zjnu;


import edu.zjnu.biz.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * main 方法入口
 *
 * @Date 2024-10-25 14:16
 * @Author 杨海波
 **/
public class App {


    public static void main(String[] args) {
        // 加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取 Bean 并使用
        MyService myService = context.getBean(MyService.class);
        myService.doSomething();
    }
}
