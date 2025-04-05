package org.spring.springboot.metrcis.impl;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.spring.springboot.metrcis.AbstractMetric;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计量器
 * <p>
 * Gauge 是一种度量器，能够监控一个可以增加和减少的动态值。
 * 该类利用 AtomicInteger 来保持 Gauge 的当前值，并提供对该值的增减操作。
 *
 * @Date 2024-09-12 14:12
 * @Author 杨海波
 */
public class GaugeMetric extends AbstractMetric {

    /**
     * 使用 AtomicInteger 保存 Gauge 的值
     */
    private final AtomicInteger gauge;

    public GaugeMetric(MeterRegistry meterRegistry, String name, Tags tags) {
        super(meterRegistry, name);
        this.gauge = new AtomicInteger(0);
        meterRegistry.gauge(name, tags, gauge);
    }

    @Override
    public void increment() {
        gauge.incrementAndGet();
    }

    @Override
    public void increment(double amount) {
        throw new UnsupportedOperationException("Gauge does not support increment(double) operation");
    }

    @Override
    public void record(long duration) {
        throw new UnsupportedOperationException("Gauge does not support record operation");
    }

    public void decrement() {
        gauge.decrementAndGet();
    }
}