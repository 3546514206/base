package edu.zjnu;


import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Main
 *
 * @Date 2024-08-20 00:48
 * @Author 杨海波
 **/
public class MicroMeterExample {

    public static void main(String[] args) throws InterruptedException {
        // 1. 创建一个 MeterRegistry 实例
        MeterRegistry registry = new SimpleMeterRegistry();

        // 2. 创建基本的 Counter 计数器
        Counter requestCounter = Counter.builder("app.requests")
                .description("Total requests processed")
                .tags("type", "basic")
                .register(registry);

        // 3. 创建 Timer 计时器
        Timer requestTimer = Timer.builder("app.request.timer")
                .description("Request processing time")
                .tags("region", "us-east")
                .register(registry);

        // 4. 使用 Gauge 监控动态值（例如内存使用）
        Gauge memoryUsageGauge = Gauge.builder("app.memory.usage", Runtime.getRuntime(), Runtime::totalMemory)
                .description("JVM total memory")
                .baseUnit("bytes")
                .tags("env", "dev")
                .register(registry);

        // 5. 高阶用法: 使用 Distribution Summary 收集分布数据
        DistributionSummary requestSizeSummary = DistributionSummary.builder("app.request.size")
                .description("Request sizes in bytes")
                .baseUnit("bytes")
                .tags("type", "high-level")
                .scale(1.0)
                .register(registry);

        Random random = new Random();

        // 模拟多个请求的处理
        for (int i = 0; i < 10; i++) {
            // 模拟请求计数
            requestCounter.increment();

            // 模拟请求大小并记录到分布摘要中
            double requestSize = random.nextDouble() * 1024;
            requestSizeSummary.record(requestSize);

            // 模拟计时器监控代码执行时间
            requestTimer.record(() -> {
                try {
                    // 模拟请求处理时间
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            // 打印当前指标状态
            System.out.println("请求计数: " + requestCounter.count());
            System.out.println("最新请求大小记录 (bytes): " + requestSizeSummary.count() + " 次, 总和: " + requestSizeSummary.totalAmount());
            System.out.println("当前计时器记录次数: " + requestTimer.count());
        }

        // 导出和查看所有指标
        registry.getMeters().forEach(meter -> {
            System.out.println("指标: " + meter.getId().getName() + " -> " + meter.measure());
        });
    }
}