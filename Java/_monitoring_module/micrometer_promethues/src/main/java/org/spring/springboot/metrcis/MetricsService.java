package org.spring.springboot.metrcis;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.spring.springboot.metrcis.impl.GaugeMetric;
import org.spring.springboot.metrcis.impl.SummaryMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Metrics 指标统计服务
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
        String key = type.name().toLowerCase(Locale.ROOT);
        return metricsMap.computeIfAbsent(key, k -> metricFactory.createMetric(type, name, tags));
    }

    /**
     * 计数器加1
     *
     * @param name
     * @param tags
     */
    public void incrementCounter(String name, Tags tags) {
        Metric counter = getOrCreateMetric(MetricType.COUNTER, name, tags);
        counter.increment();
    }

    /**
     * 计数器加指定值
     *
     * @param name
     * @param tags
     * @param amount
     */
    public void incrementCounterByAmount(String name, Tags tags, double amount) {
        Metric counter = getOrCreateMetric(MetricType.COUNTER, name, tags);
        counter.increment(amount);
    }

    /**
     * 记录耗时
     *
     * @param name
     * @param tags
     * @param duration
     */
    public void recordTimer(String name, Tags tags, long duration) {
        Metric timer = getOrCreateMetric(MetricType.TIMER, name, tags);
        timer.record(duration);
    }

    /**
     * 计量器加一
     *
     * @param name
     * @param tags
     */
    public void incrementGauge(String name, Tags tags) {
        Metric gauge = getOrCreateMetric(MetricType.GAUGE, name, tags);
        gauge.increment();
    }

    /**
     * 计量器减一
     *
     * @param name
     * @param tags
     */
    public void decrementGauge(String name, Tags tags) {
        Metric gauge = getOrCreateMetric(MetricType.GAUGE, name, tags);
        ((GaugeMetric) gauge).decrement();
    }

    /**
     * 向摘要器添加样本值
     *
     * @param name
     * @param tags
     * @param value
     */
    public void recordSummary(String name, Tags tags, double value) {
        Metric summary = getOrCreateMetric(MetricType.SUMMARY, name, tags);
        summary.increment(value);
    }

    /**
     * 通过摘要器获取平均值
     *
     * @param name
     * @param tags
     * @return
     */
    public double getSummaryMean(String name, Tags tags) {
        SummaryMetric summary = (SummaryMetric) getOrCreateMetric(MetricType.SUMMARY, name, tags);
        return summary.getMean();
    }

    /**
     * 通过摘要器获取最大值
     *
     * @param name
     * @param tags
     * @return
     */
    public double getSummaryMax(String name, Tags tags) {
        SummaryMetric summary = (SummaryMetric) getOrCreateMetric(MetricType.SUMMARY, name, tags);
        return summary.getMax();
    }

    /**
     * 创建摘要器以来被调用的次数
     *
     * @param name
     * @param tags
     * @return
     */
    public long getSummaryCount(String name, Tags tags) {
        SummaryMetric summary = (SummaryMetric) getOrCreateMetric(MetricType.SUMMARY, name, tags);
        return summary.getCount();
    }

    /**
     * 通过摘要器计算样本之和
     *
     * @param name
     * @param tags
     * @return
     */
    public double getSummaryTotalAmount(String name, Tags tags) {
        SummaryMetric summary = (SummaryMetric) getOrCreateMetric(MetricType.SUMMARY, name, tags);
        return summary.getTotalAmount();
    }

}