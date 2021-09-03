package edu.zjnu.spring.enviroment;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @description: EnvironmentAware
 * @author: 杨海波
 * @date: 2021-09-03
 **/
public class EnvironmentHelper implements EnvironmentAware {

    private Environment environment;

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
