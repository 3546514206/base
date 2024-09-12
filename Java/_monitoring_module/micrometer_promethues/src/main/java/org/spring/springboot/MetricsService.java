package org.spring.springboot;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * todo 
 *
 * @Date 2024-09-12 14:15
 * @Author 杨海波
 **/
@Service
public class MetricsService {

    private final MetricFactory metricFactory;
    private final ConcurrentMap<String, Metric> metricsMap = new ConcurrentHashMap<>();

    @Autowired
    public MetricsService(MeterRegistry meterRegistry) {
        this.metricFactory = new MetricFactory(meterRegistry);
    }

    private Metric getOrCreateMetric(MetricType type, String name, Tags tags) {
        String key = type.name() + ":" + name + ":" + tags.toString();
        return metricsMap.computeIfAbsent(key, k -> metricFactory.createMetric(type, name, tags));
    }

    public void incrementCounter(String name, Tags tags) {
        Metric counter = getOrCreateMetric(MetricType.COUNTER, name, tags);
        counter.increment();
    }

    public void incrementCounterByAmount(String name, Tags tags, double amount) {
        Metric counter = getOrCreateMetric(MetricType.COUNTER, name, tags);
        counter.increment(amount);
    }

    public void recordTimer(String name, Tags tags, long duration) {
        Metric timer = getOrCreateMetric(MetricType.TIMER, name, tags);
        timer.record(duration);
    }

    public void incrementGauge(String name, Tags tags) {
        Metric gauge = getOrCreateMetric(MetricType.GAUGE, name, tags);
        gauge.increment();
    }

    public void decrementGauge(String name, Tags tags) {
        Metric gauge = getOrCreateMetric(MetricType.GAUGE, name, tags);
        ((GaugeMetric) gauge).decrement();
    }

    public void recordHistogram(String name, Tags tags, double value) {
        Metric histogram = getOrCreateMetric(MetricType.HISTOGRAM, name, tags);
        histogram.increment(value);
    }

    public void recordSummary(String name, Tags tags, long duration) {
        Metric summary = getOrCreateMetric(MetricType.SUMMARY, name, tags);
        summary.record(duration);
    }
}