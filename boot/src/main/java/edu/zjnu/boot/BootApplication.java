package edu.zjnu.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BootApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
