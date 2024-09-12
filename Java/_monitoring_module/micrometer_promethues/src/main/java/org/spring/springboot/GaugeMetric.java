package org.spring.springboot;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * todo
 *
 * @Date 2024-09-12 14:12
 * @Author 杨海波
 **/
// 并发数Gauge实现类
class GaugeMetric extends AbstractMetric {
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