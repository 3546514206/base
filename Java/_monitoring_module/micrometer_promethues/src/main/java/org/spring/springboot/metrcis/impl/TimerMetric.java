package org.spring.springboot.metrcis.impl;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import org.spring.springboot.metrcis.AbstractMetric;

/**
 * 计时器
 * <p>
 * Timer 是一种度量器，用于记录时间持续量，常用于跟踪方法的执行时间或某些操作的耗时。
 * 该类提供了记录时间的功能，但不支持增量操作。
 *
 * @Date 2024-09-12 12:25
 * @Author 杨海波
 **/
public class TimerMetric extends AbstractMetric {

    /**
     * Timer 实例，用于记录时间
     */
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

    /**
     * 记录时间持续量。
     * <p>
     * 记录某个操作的持续时间（以毫秒为单位）到 Timer 中。
     *
     * @param duration 持续时间（long 类型）。
     */
    @Override
    public void record(long duration) {
        timer.record(duration, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
}