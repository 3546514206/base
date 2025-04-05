package org.spring.springboot.metrcis.impl;


import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.spring.springboot.metrcis.AbstractMetric;

/**
 * 摘要器
 * <p>
 * 用于记录和统计一组数值的分布情况。通常用于测量不需要计数的、但需要了解其分布特征的度量，例如响应时间、请求大小等。
 *
 * @Date 2024-09-12 14:13
 * @Author 杨海波
 **/
public class SummaryMetric extends AbstractMetric {


    private final DistributionSummary summary;

    public SummaryMetric(MeterRegistry meterRegistry, String name, Tags tags) {
        super(meterRegistry, name);
        this.summary = DistributionSummary.builder(name).tags(tags).register(meterRegistry);
    }

    @Override
    public void increment() {
        throw new UnsupportedOperationException("Summary does not support increment operation without a value");
    }

    /**
     * 记录每个事件的值
     *
     * @param amount
     */
    @Override
    public void increment(double amount) {
        summary.record(amount);
    }

    @Override
    public void record(long duration) {
        throw new UnsupportedOperationException("Use increment(double) to record values for Summary");
    }

    /**
     * 获取统计数据
     *
     * @return
     */
    public double getMean() {
        return summary.mean();
    }

    /**
     * 返回平均值
     *
     * @return
     */
    public double getMax() {
        return summary.max();
    }

    /**
     * 返回事件总数
     *
     * @return
     */
    public long getCount() {
        return summary.count();
    }

    /**
     * 返回所有事件值的总和
     *
     * @return
     */
    public double getTotalAmount() {
        return summary.totalAmount();
    }
}