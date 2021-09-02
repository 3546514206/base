package edu.zjnu.spring.aop.factorybean;

import java.math.BigDecimal;

/**
 * @description: Car
 * @author: 杨海波
 * @date: 2021-09-02
 **/
public class Car {

    private String maxSpeed;

    private BigDecimal price;

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
