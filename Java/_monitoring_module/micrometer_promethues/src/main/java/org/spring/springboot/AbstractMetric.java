package org.spring.springboot;


import io.micrometer.core.instrument.MeterRegistry;

/**
 * todo
 *
 * @Date 2024-09-12 11:38
 * @Author 杨海波
 **/
// 指标基础类，持有公用的 MeterRegistry 实例
abstract class AbstractMetric implements Metric {
    protected final MeterRegistry meterRegistry;
    protected final String name;

    public AbstractMetric(MeterRegistry meterRegistry, String name) {
        this.meterRegistry = meterRegistry;
        this.name = name;
    }
}