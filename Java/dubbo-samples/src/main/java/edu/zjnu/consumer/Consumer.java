package edu.zjnu.consumer;


import edu.zjnu.provider.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * todo
 *
 * @Date 2025-03-06 15:09
 * @Author 杨海波
 **/
public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        EchoService demoService = (EchoService) context.getBean("echoService"); // 获取远程服务代理
        String hello = demoService.sayHello("world");
        // 显示调用结果
        System.out.println(hello);
    }

}
