package org.spring.springboot;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;

/**
 * todo
 *
 * @Date 2024-09-12 12:25
 * @Author 杨海波
 **/
// 定时器实现类
class TimerMetric extends AbstractMetric {
    private final Timer timer;

    public TimerMetric(MeterRegistry meterRegistry, String name, Tags tags) {
        super(meterRegistry, name);
        this.timer = Timer.builder(name).tags(tags).register(meterRegistry);
    }

    @Override
    public void increment() {
        throw new UnsupportedOperationException("Timer does not support increment operation");
    }

    @Override
    public void increment(double amount) {
        throw new UnsupportedOperationException("Timer does not support increment operation");
    }

    @Override
    public void record(long duration) {
        timer.record(duration, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
}