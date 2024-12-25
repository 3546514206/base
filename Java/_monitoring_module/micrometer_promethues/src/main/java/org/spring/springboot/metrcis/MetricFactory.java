package org.spring.springboot.metrcis;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.spring.springboot.metrcis.impl.CounterMetric;
import org.spring.springboot.metrcis.impl.GaugeMetric;
import org.spring.springboot.metrcis.impl.SummaryMetric;
import org.spring.springboot.metrcis.impl.TimerMetric;

/**
 * 指标器工厂类
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
                // 创建计数器
                return new CounterMetric(meterRegistry, name, tags);
            case TIMER:
                // 创建计时器
                return new TimerMetric(meterRegistry, name, tags);
            case GAUGE:
                // 创建计量器
                return new GaugeMetric(meterRegistry, name, tags);
            case SUMMARY:
                // 创建摘要器
                return new SummaryMetric(meterRegistry, name, tags);
            default:
                throw new MetricException("Unsupported metric type: " + type);
        }
    }
}