package edu.zjnu;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * todo
 *
 * @Date 2024-08-29 17:42
 * @Author 杨海波
 **/
public class PrometheusMetricsExample {


    public static void main(String[] args) {
        // 创建一个简单的 MeterRegistry 实例
        MeterRegistry registry = new SimpleMeterRegistry();

        // 创建计数器
        Counter counter = registry.counter("my_counter", "type", "example");

        // 创建计时器
        Timer timer = registry.timer("my_timer", "type", "example");

        // 模拟计数器增加
        counter.increment();
        counter.increment();

        // 模拟计时器的测量
        Timer.Sample sample = Timer.start(registry);
        try {
            // 模拟一些工作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        sample.stop(timer);

        // 打印 Prometheus 格式的指标
        System.out.println(exportMetricsAsPrometheus(registry));
    }

    private static String exportMetricsAsPrometheus(MeterRegistry registry) {
        StringBuilder sb = new StringBuilder();
        registry.getMeters().forEach(meter -> {
            meter.measure().forEach(measurement -> {
                sb.append("# TYPE ").append(meter.getId().getName()).append(" gauge\n");
                sb.append(meter.getId().getName()).append("{").append(meter.getId()).append("} ")
                        .append(measurement.getValue()).append("\n");
            });
        });
        return sb.toString();
    }
}
