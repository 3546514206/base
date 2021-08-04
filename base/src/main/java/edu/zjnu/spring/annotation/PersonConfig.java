package edu.zjnu.spring.annotation;

import edu.zjnu.spring.ioc.common.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 杨海波
 * @description 该配置类对Person进行配置
 * @date 2021-02-18 13:22
 */
@Configuration
public class PersonConfig {

    @Bean(name = "person")
    public Person getPerson() {
        Person person = new Person();
        person.setAge(21);
        person.setName("杨海波");

        return person;
    }

}
