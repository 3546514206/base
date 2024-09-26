package org.spring.springboot;

import io.micrometer.core.instrument.Tags;
import org.spring.springboot.metrcis.MetricConstants;
import org.spring.springboot.metrcis.MetricsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;


@RestController
public class UserController {

    @Resource
    private MetricsService metricsService;

    private static int getRandom() {
        // 生成 1 到 999 之间的随机数
        return new Random().nextInt(999) + 1;
    }

    @GetMapping("/user/normal")
    public String normal() {
        String endpoint = "normal";
        Tags tags = Tags.of("endpoint", endpoint);

        // 请求计数器 +1
        metricsService.incrementCounter(MetricConstants.REQUESTS_COUNT, tags);

        metricsService.incrementGauge(MetricConstants.REQUESTS_CONCURRENT, tags);
        long startTime = System.currentTimeMillis();

        try {
            // 模拟耗时操作
            Thread.sleep(30);
            return "normal Successful";
        } catch (Exception e) {
            metricsService.incrementCounter(MetricConstants.REQUESTS_EXCEPTIONS_COUNT, tags);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            metricsService.recordTimer(MetricConstants.REQUESTS_TIMER, tags, duration);
            // 对耗时的摘要
            metricsService.recordSummary(MetricConstants.REQUESTS_SUMMARY, tags, duration);
            metricsService.decrementGauge(MetricConstants.REQUESTS_CONCURRENT, tags);
        }

        return "normal Successful";
    }

    @GetMapping("/user/summary")
    public String summary() {
        String endpoint = "summary";
        Tags tags = Tags.of("endpoint", endpoint);
        int random = getRandom();

        try {
            // 模拟耗时操作
            Thread.sleep(30);
            return "summary Successful";
        } catch (Exception e) {
            metricsService.incrementCounter(MetricConstants.REQUESTS_EXCEPTIONS_COUNT, tags);
        } finally {
            // 对耗时的摘要
            metricsService.recordSummary(MetricConstants.REQUESTS_SUMMARY, tags, random);
        }

        // 获取并输出直方图统计信息
        double mean = metricsService.getSummaryMean(MetricConstants.REQUESTS_SUMMARY, tags);
        double max = metricsService.getSummaryMax(MetricConstants.REQUESTS_SUMMARY, tags);
        double count = metricsService.getSummaryCount(MetricConstants.REQUESTS_SUMMARY, tags);
        double amount = metricsService.getSummaryTotalAmount(MetricConstants.REQUESTS_SUMMARY, tags);
        System.out.println("mean:" + mean + "|" + "max:" + max + "|" + "count:" + count + "|" + "amount:" + amount);

        return "summary Successful";
    }
}
