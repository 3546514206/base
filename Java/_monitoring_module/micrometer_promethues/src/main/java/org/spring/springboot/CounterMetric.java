package org.spring.springboot;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

/**
 * todo
 *
 * @Date 2024-09-12 11:39
 * @Author 杨海波
 **/
// 计数器实现类
class CounterMetric extends AbstractMetric {
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
