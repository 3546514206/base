package org.spring.springboot;


import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

/**
 * todo
 *
 * @Date 2024-09-12 14:12
 * @Author 杨海波
 **/
// 直方图实现类
class HistogramMetric extends AbstractMetric {
    private final DistributionSummary histogram;

    public HistogramMetric(MeterRegistry meterRegistry, String name, Tags tags) {
        super(meterRegistry, name);
        this.histogram = DistributionSummary.builder(name).tags(tags).register(meterRegistry);
    }

    @Override
    public void increment() {
        throw new UnsupportedOperationException("Histogram does not support increment operation");
    }

    @Override
    public void increment(double amount) {
        histogram.record(amount);
    }

    @Override
    public void record(long duration) {
        histogram.record(duration);
    }
}
