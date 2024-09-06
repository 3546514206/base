package org.spring.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 杨海波
 * @date: 2023-02-14 17:18:29
 * @description:
 */
@RestController
public class UserController {

    @Autowired
    private MetricsService metricsService;

    @GetMapping("/user/login")
    public String login() {
        String endpoint = "login";
        long startTime = System.currentTimeMillis();

        // 记录并发请求数增加
        metricsService.incrementConcurrency(endpoint, MetricsService.BizType.WEB);
        // 记录请求开始
        metricsService.incrementRequestCount(endpoint, MetricsService.BizType.WEB);

        try {
            // 模拟处理逻辑
            Thread.sleep(30); // 模拟耗时操作
            return "Login Successful";
        } catch (Exception e) {
            // 记录异常计数
            metricsService.incrementExceptionCount(endpoint, MetricsService.BizType.WEB);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            // 记录请求耗时
            metricsService.recordRequestTime(endpoint, MetricsService.BizType.WEB, duration);
            // 记录并发请求数减少
            metricsService.decrementConcurrency(endpoint, MetricsService.BizType.WEB);
        }

        return "login";
    }

    @GetMapping("/user/getUserInfo")
    public String getUserInfo() {
        String endpoint = "getUserInfo";
        long startTime = System.currentTimeMillis();

        // 记录并发请求数增加
        metricsService.incrementConcurrency(endpoint, MetricsService.BizType.WEB);
        // 记录请求开始
        metricsService.incrementRequestCount(endpoint, MetricsService.BizType.WEB);

        try {
            // 模拟处理逻辑
            Thread.sleep(30); // 模拟耗时操作
            return "User Info";
        } catch (Exception e) {
            // 记录异常计数
            metricsService.incrementExceptionCount(endpoint, MetricsService.BizType.WEB);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            // 记录请求耗时
            metricsService.recordRequestTime(endpoint, MetricsService.BizType.WEB, duration);
            // 记录并发请求数减少
            metricsService.decrementConcurrency(endpoint, MetricsService.BizType.WEB);
        }

        return "UserInfo";
    }
}
