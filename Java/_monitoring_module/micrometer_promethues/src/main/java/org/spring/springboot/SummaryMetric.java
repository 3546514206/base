package org.spring.springboot;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;

/**
 * todo
 *
 * @Date 2024-09-12 14:13
 * @Author 杨海波
 **/
// 摘要实现类
class SummaryMetric extends AbstractMetric {
    private final Timer summary;

    public SummaryMetric(MeterRegistry meterRegistry, String name, Tags tags) {
        super(meterRegistry, name);
        this.summary = Timer.builder(name).tags(tags).register(meterRegistry);
    }

    @Override
    public void increment() {
        throw new UnsupportedOperationException("Summary does not support increment operation");
    }

    @Override
    public void increment(double amount) {
        throw new UnsupportedOperationException("Summary does not support increment(double) operation");
    }

    @Override
    public void record(long duration) {
        summary.record(duration, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
}