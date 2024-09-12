package org.spring.springboot;


/**
 * todo
 *
 * @Date 2024-09-12 11:37
 * @Author 杨海波
 **/
// 保存所有指标名称常量的接口
public interface MetricConstants {
    String REQUESTS_COUNT = "requests_count";
    String REQUESTS_EXCEPTIONS_COUNT = "requests_exceptions_count";
    String REQUESTS_TIMER = "requests_timer";
    String REQUESTS_CONCURRENT = "requests_concurrent";
    String REQUESTS_HISTOGRAM = "requests_histogram";
    String REQUESTS_SUMMARY = "requests_summary";
    // 可以继续添加更多的指标名称常量...
}
