package org.spring.springboot.metrcis.impl;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.spring.springboot.metrcis.AbstractMetric;

/**
 * 计数器
 * <p>
 * CounterMetric 监控单调递增的值。计数器的值不能重置为较小的值。
 *
 * @Date 2024-09-12 11:39
 * @Author 杨海波
 **/
public class CounterMetric extends AbstractMetric {

    private final Counter counter;

    public CounterMetric(MeterRegistry meterRegistry, String name, Tags tags) {
        super(meterRegistry, name);
        this.counter = Counter.builder(name).tags(tags).register(meterRegistry);
    }

    @Override
    public void increment() {
        counter.increment();
    }

    @Override
    public void increment(double amount) {
        counter.increment(amount);
    }

    @Override
    public void record(long duration) {
        throw new UnsupportedOperationException("Counter does not support record operation");
    }
}
