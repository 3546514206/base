package edu.zjnu.spring.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @description: BeanPostLogProcessor
 * @author: 杨海波
 * @date: 2021-09-03
 **/
public class BeanPostLogProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("开始处理bean，被处理的bean是：" + beanName);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + ": 已经处理完毕");
        return bean;
    }
}
