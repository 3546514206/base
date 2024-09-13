package org.spring.springboot.metrcis;


import io.micrometer.core.instrument.MeterRegistry;

/**
 * 指标基础类，持有公用的 MeterRegistry 实例
 *
 * @Date 2024-09-12 11:38
 * @Author 杨海波
 **/
public abstract class AbstractMetric implements Metric {

    /**
     * 公用的 MeterRegistry 实例
     */
    protected final MeterRegistry meterRegistry;

    /**
     * 指标名称
     */
    protected final String name;

    public AbstractMetric(MeterRegistry meterRegistry, String name) {
        this.meterRegistry = meterRegistry;
        this.name = name;
    }
}