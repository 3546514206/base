package edu.zjnu.spring.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @description: SampleServiceNameProcessor
 * @author: 杨海波
 * @date: 2021-09-03
 **/
public class SampleServiceNameProcessor implements BeanPostProcessor {

    private String name;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof NameIniter) {
            ((NameIniter) bean).setName(name);
        }

        return bean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
