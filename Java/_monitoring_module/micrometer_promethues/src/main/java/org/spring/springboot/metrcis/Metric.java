package org.spring.springboot.metrcis;


/**
 * 通用指标接口
 *
 * @Date 2024-09-12 11:38
 * @Author 杨海波
 **/

public interface Metric {

    void increment();

    void increment(double amount);

    void record(long duration);
}