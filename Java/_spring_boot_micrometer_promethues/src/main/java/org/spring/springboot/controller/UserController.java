package org.spring.springboot.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.spring.springboot.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 杨海波
 * @date: 2023-02-14 17:18:29
 * @description: 
 */
@RestController
public class UserController {


    // 创建一个 Counter 计数器
    @Resource
    private Counter counter;

    @Resource
    private Timer timer;


    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) {
        counter.increment();
        // 使用 Timer 计时器测量响应时间
        return timer.record(() -> {
            // 每次调用 /api/hello 时递增计数器
            counter.increment();
            try {
                // 模拟处理延迟
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return user;
        });

    }
}
