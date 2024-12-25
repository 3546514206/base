package org.spring.springboot.metrcis;


/**
 * 保存所有指标名称常量的接口
 *
 * @Date 2024-09-12 11:37
 * @Author 杨海波
 **/
public interface MetricConstants {

    /**
     * 对服务进行请求的总量
     */
    String REQUESTS_COUNT = "requests_count";

    /**
     * 对服务进行请求的异常请求总量
     */
    String REQUESTS_EXCEPTIONS_COUNT = "requests_exceptions_count";

    /**
     * 对服务进行请求的耗时
     */
    String REQUESTS_TIMER = "requests_timer";

    /**
     * 对服务进行请求的并发
     */
    String REQUESTS_CONCURRENT = "requests_concurrent";

    /**
     * 对服务进行请求的摘要
     */
    String REQUESTS_SUMMARY = "requests_summary";

    /**
     * 对服务进行请求的摘要
     */
    String REQUESTS_SUMMARY_MEAN = "requests_summary_mean";

    /**
     * 对服务进行请求的摘要
     */
    String REQUESTS_SUMMARY_MAX = "requests_summary_max";

    /**
     * 对服务进行请求的摘要
     */
    String REQUESTS_SUMMARY_COUNT = "requests_summary_count";

    /**
     * 对服务进行请求的摘要
     */
    String REQUESTS_SUMMARY_TOTAL_AMOUNT = "requests_summary_total_amount";
}
