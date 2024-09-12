package org.spring.springboot;

import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;


@RestController
public class UserController {

    @Resource
    private MetricsService metricsService;

    @GetMapping("/user/login")
    public String login() {
        String endpoint = "login";
        Tags tags = Tags.of("endpoint", endpoint);

        metricsService.incrementCounter(MetricConstants.REQUESTS_COUNT, tags);
        metricsService.incrementGauge(MetricConstants.REQUESTS_CONCURRENT, tags);

        long startTime = System.currentTimeMillis();

        try {
            // 模拟耗时操作
            Thread.sleep(30);
            // 记录直方图数据
            metricsService.recordHistogram(MetricConstants.REQUESTS_HISTOGRAM, tags, 30);
            return "Login Successful";
        } catch (Exception e) {
            metricsService.incrementCounter(MetricConstants.REQUESTS_EXCEPTIONS_COUNT, tags);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            metricsService.recordTimer(MetricConstants.REQUESTS_TIMER, tags, duration);
            metricsService.recordSummary(MetricConstants.REQUESTS_SUMMARY, tags, duration);
            metricsService.decrementGauge(MetricConstants.REQUESTS_CONCURRENT, tags);
        }

        return "login";
    }
}
