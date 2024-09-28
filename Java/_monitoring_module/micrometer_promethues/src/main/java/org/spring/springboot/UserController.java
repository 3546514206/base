package org.spring.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class UserController {

    @Resource
    private DefaultMetricsClient metricsClient;

    @GetMapping("/user/normal")
    public String normal() {
        String endpoint = "normal";
        // 请求计数器 +1
        DefaultMetricsContext metricsContext = new DefaultMetricsContext(endpoint);
        metricsClient.recordBeginMetric(metricsContext);
        long startTime = System.currentTimeMillis();

        try {
            // 模拟耗时操作
            Thread.sleep(30);
            return "normal Successful";
        } catch (Exception e) {
            metricsClient.recordExceptionMetric(metricsContext);
        } finally {
           metricsClient.recordFinallyMetric(metricsContext);
        }

        return "normal Successful";
    }

}
