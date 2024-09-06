package org.spring.springboot;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MetricsService
 *
 * @Date 2024-09-05 09:24
 * @Author 杨海波
 **/
@Component
public class MetricsService {

    /**
     * micrometer 中管理所有度量指标的集合
     */
    private final MeterRegistry meterRegistry;

    // 使用ConcurrentMap来存储不同类型的指标
    private final ConcurrentMap<String, Counter> counters = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Timer> timers = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, AtomicInteger> gauges = new ConcurrentHashMap<>();

    @Autowired
    public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    // 定义内部枚举来区分调用场景，并定义指标名称
    public enum BizType {
        WEB("web"), RPC("rpc");

        private final String prefix;

        BizType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    // 获取或创建一个通用计数器
    public Counter getCounter(String metricName, String transCode, BizType bizType) {
        String fullMetricName = bizType.getPrefix() + "." + metricName;
        return counters.computeIfAbsent(fullMetricName + ":" + transCode, key -> createCounter(fullMetricName, transCode));
    }

    // 创建计数器
    private Counter createCounter(String metricName, String transCode) {
        return Counter.builder(metricName)
                .tag("transCode", transCode)
                .description("Counter for " + transCode)
                .register(meterRegistry);
    }

    // 获取或创建一个通用定时器
    public Timer getTimer(String metricName, String tag, BizType bizType) {
        String fullMetricName = bizType.getPrefix() + "_" + metricName;
        return timers.computeIfAbsent(fullMetricName + "_" + tag, key -> createTimer(fullMetricName, tag));
    }

    // 创建定时器
    private Timer createTimer(String metricName, String transCode) {
        return Timer.builder(metricName)
                .tag("transCode", transCode)
                .description("Timer for " + transCode)
                .register(meterRegistry);
    }

    // 获取或创建一个通用Gauge（用于监控并发数等）
    public AtomicInteger getGauge(String metricName, String transCode, BizType bizType) {
        String fullMetricName = bizType.getPrefix() + "_" + metricName;
        return gauges.computeIfAbsent(fullMetricName + "_" + transCode, key -> createGauge(fullMetricName, transCode));
    }

    // 创建Gauge
    private AtomicInteger createGauge(String metricName, String tag) {
        AtomicInteger gauge = new AtomicInteger(0);
        meterRegistry.gauge(metricName, Tags.of("transCode", tag), gauge);
        return gauge;
    }

    // 记录请求计数
    public void incrementRequestCount(String transCode, BizType bizType) {
        getCounter("requests_count", transCode, bizType).increment();
    }

    // 记录异常计数
    public void incrementExceptionCount(String transCode, BizType bizType) {
        getCounter("requests_exceptions_count", transCode, bizType).increment();
    }

    // 记录请求耗时
    public void recordRequestTime(String transCode, BizType bizType, long durationInMillis) {
        getTimer("requests_timer", transCode, bizType).record(durationInMillis, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    // 增加并发请求数
    public void incrementConcurrency(String transCode, BizType bizType) {
        getGauge("requests_concurrent", transCode, bizType).incrementAndGet();
    }

    // 减少并发请求数
    public void decrementConcurrency(String transCode, BizType bizType) {
        getGauge("requests_concurrent", transCode, bizType).decrementAndGet();
    }
}
