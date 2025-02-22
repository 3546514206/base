package edu.zjnu.biz.service;


import edu.zjnu.biz.myannotation.CustomComponent;

/**
 * MyService
 *
 * @Date 2024-10-28 16:29
 * @Author 杨海波
 **/
// @Component

// CustomComponent 注解在这里是无法生效的，但是可以在 SpringBoot 中可以生效，这是因为 在 Spring Boot 应用中，默认的组件扫描行为是扫描
// 主应用类（带有 @SpringBootApplication 注解的类）所在的包以及其子包中的所有带有 @Component 及其派生注解（如 @Service,
// @Repository 等）的类。
@CustomComponent
public class MyService {

    public void doSomeThing() {
        System.out.println("doing somethings...");
    }
}
