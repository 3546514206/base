package edu.zjnu.biz;


import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/**
 * MyService
 *
 * @Date 2024-10-25 14:17
 * @Author 杨海波
 **/
@Component
public class RetryService {

    // 直接使用 @Retryable 注解，不需要配置类
    @Retryable(
            value = {RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public void doSomething(Integer attempt) {

        // 模拟失败的场景
        if (attempt < 3) {
            throw new RuntimeException("API call failed");
        }

        System.out.println("API call succeeded");
    }
}
