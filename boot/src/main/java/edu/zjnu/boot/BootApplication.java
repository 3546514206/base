package edu.zjnu.boot;

import edu.zjnu.boot.config.BootConfiguration;
import edu.zjnu.boot.config.NameConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BootApplication.class, args);
        context.getBean(NameConfigBean.class);
        System.out.println(context.getBean(NameConfigBean.class));
        System.out.println(context.getBean(BootConfiguration.class));

//        SpringApplication application = new SpringApplication(BootApplication.class);
//        application.run(args);
    }

}
