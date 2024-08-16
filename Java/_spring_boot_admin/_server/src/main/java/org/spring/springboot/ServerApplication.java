package org.spring.springboot;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 杨海波
 * @date: 2023-02-14 17:20:14
 * @description: 启动类
 */
@Configuration
@EnableAdminServer
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class,args);
    }
}
