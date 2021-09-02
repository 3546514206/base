package edu.zjnu.spring.aop.factorybean;

import org.springframework.beans.factory.FactoryBean;

import java.math.BigDecimal;

/**
 * @description: CarFactoryBean
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class CarFactoryBean implements FactoryBean<Car> {

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setMaxSpeed("400");
        car.setPrice(new BigDecimal("200"));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
