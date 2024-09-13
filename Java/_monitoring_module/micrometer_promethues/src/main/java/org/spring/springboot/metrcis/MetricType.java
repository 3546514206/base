package org.spring.springboot.metrcis;


/**
 * Metric 数据类型
 *
 * @Date 2024-09-12 11:37
 * @Author 杨海波
 **/
public enum MetricType {

    /**
     * 计数器
     */
    COUNTER,

    /**
     * 计时器
     */
    TIMER,

    /**
     * 计量器
     */
    GAUGE,

    /**
     * 摘要
     */
    SUMMARY
}