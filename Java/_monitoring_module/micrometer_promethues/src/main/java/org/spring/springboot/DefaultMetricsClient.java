package org.spring.springboot;

import io.micrometer.core.instrument.Tags;
import org.spring.springboot.metrcis.MetricConstants;
import org.spring.springboot.metrcis.MetricsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * DefaultMetricsClient
 * <p>
 * 以交易为维度的通讯调用指标监控默认实现
 * <p>
 * 其他更多的监控场景和监控需求，例如请求成功率之类的，需要用户基于 MetricsService 自行实现
 *
 * @author 杨海波
 */
@Component
public class DefaultMetricsClient {

    @Resource
    private MetricsService metricsService;

    /**
     * 开始请求前的监控指标记录
     */
    public void recordBeginMetric(DefaultMetricsContext metricsCtx) {
        // 记录一些需要用到的基本监控参数
        Tags tags = Tags.of("bizCode", metricsCtx.getBizCode());
        metricsCtx.setTags(tags);
        Long startTime = System.currentTimeMillis();
        metricsCtx.setStartTime(startTime);

        // 计数器 +1
        metricsService.incrementCounter(MetricConstants.REQUESTS_COUNT, tags);
        // 计量器 + 1，表示并发 + 1
        metricsService.incrementGauge(MetricConstants.REQUESTS_CONCURRENT, tags);
    }

    /**
     * 对异常情况的记录
     *
     * @param metricsCtx
     */
    public void recordExceptionMetric(DefaultMetricsContext metricsCtx) {
        // 异常请求计数器 + 1
        metricsService.incrementCounter(MetricConstants.REQUESTS_EXCEPTIONS_COUNT, metricsCtx.getTags());
    }

    /**
     * 请求结束后的记录
     *
     * @param metricsCtx
     */
    public void recordFinallyMetric(DefaultMetricsContext metricsCtx) {
        // 通讯时长
        long duration = metricsCtx.getStartTime() - System.currentTimeMillis();
        // 记录通讯时长
        metricsService.recordTimer(MetricConstants.REQUESTS_TIMER, metricsCtx.getTags(), duration);
        // 对耗时的基本摘要
        metricsService.recordSummary(MetricConstants.REQUESTS_SUMMARY, metricsCtx.getTags(), duration);
        // 计量器 -1，表示并发 -1
        metricsService.decrementGauge(MetricConstants.REQUESTS_CONCURRENT, metricsCtx.getTags());
    }

}
