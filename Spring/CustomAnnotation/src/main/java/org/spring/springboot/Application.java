package org.spring.springboot;

import org.spring.springboot.biz.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: 杨海波
 * @date: 2023-02-14 17:20:14
 * @description: 启动类
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Service service = context.getBean(Service.class);
        service.doSomeThing();
    }
}
