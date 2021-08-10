package edu.zjnu.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description: BootConfig
 * @author: 杨海波
 * @date: 2021-08-05
 **/
@Configuration
@PropertySource("classpath:/application.properties")
public class BootConfiguration {

    @Value("${boot.config.value}")
    private String bootConfigValue;

    @Bean
    public NameConfigBean getNameConfigBean() {
        return new NameConfigBean(bootConfigValue);
    }

}
