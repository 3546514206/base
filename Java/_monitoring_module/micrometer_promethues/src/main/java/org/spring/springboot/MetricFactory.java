package org.spring.springboot;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

/**
 * todo
 *
 * @Date 2024-09-12 14:15
 * @Author 杨海波
 **/
public class MetricFactory {
    private final MeterRegistry meterRegistry;

    public MetricFactory(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public Metric createMetric(MetricType type, String name, Tags tags) {
        switch (type) {
            case COUNTER:
                return new CounterMetric(meterRegistry, name, tags);
            case TIMER:
                return new TimerMetric(meterRegistry, name, tags);
            case GAUGE:
                return new GaugeMetric(meterRegistry, name, tags);
            case HISTOGRAM:
                return new HistogramMetric(meterRegistry, name, tags);
            case SUMMARY:
                return new SummaryMetric(meterRegistry, name, tags);
            default:
                throw new MetricException("Unsupported metric type: " + type);
        }
    }
}