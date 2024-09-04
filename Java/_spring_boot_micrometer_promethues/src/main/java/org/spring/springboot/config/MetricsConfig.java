package org.spring.springboot.config;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MetricsConfig
 *
 * @Date 2024-09-03 15:00
 * @Author 杨海波
 **/
@Configuration
public class MetricsConfig {

    // 自定义 API 调用计数器
    @Bean
    public Counter customApiCallCounter(MeterRegistry meterRegistry) {
        return Counter.builder("custom_api_calls_total")
                .description("Counts the number of API calls to /user/login")
                .tag("endpoint", "/user/login")  // 添加标签（标签可以帮助 Prometheus 按照维度进行聚合）
                .register(meterRegistry);
    }

    // 自定义 API 响应时间计时器
    @Bean
    public Timer customApiCallTimer(MeterRegistry meterRegistry) {
        return Timer.builder("custom_api_response_time")
                .description("Measures the response time of /user/login")
                .tag("endpoint", "/user/login")  // 添加标签
                .publishPercentiles(0.5, 0.95, 0.99)  // 发布百分位（p50, p95, p99）
                .publishPercentileHistogram(true)  // 发布百分位直方图
                .register(meterRegistry);
    }

}
