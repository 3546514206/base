package edu.zjnu.provider;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Provider
 *
 * @Date 2025-03-06 15:05
 * @Author 杨海波
 **/
public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        context.start();
        System.out.println("服务已启动...");
        System.in.read(); // 按任意键退出
    }
}
