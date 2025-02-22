package edu.zjnu;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * TaggingMetricsExample
 *
 * @Date 2024-08-29 17:06
 * @Author 杨海波
 **/
public class TaggingMetricsExample {

    private final Counter loginCounter;
    private final Counter logoutCounter;
    private final Timer operationTimer;

    public TaggingMetricsExample(MeterRegistry meterRegistry) {
        // Create counters with different tags
        loginCounter = meterRegistry.counter("user_operations", "operation_type", "login");
        logoutCounter = meterRegistry.counter("user_operations", "operation_type", "logout");

        // Create a timer with a tag
        operationTimer = meterRegistry.timer("operation_duration", "operation_type", "generic");
    }

    public void simulateLogin() {
        Timer.Sample sample = Timer.start();
        try {
            // Simulate user login
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sample.stop(operationTimer);
        }
        loginCounter.increment();
    }

    public void simulateLogout() {
        Timer.Sample sample = Timer.start();
        try {
            // Simulate user logout
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sample.stop(operationTimer);
        }
        logoutCounter.increment();
    }

    public static void main(String[] args) {
        MeterRegistry meterRegistry = new SimpleMeterRegistry();
        TaggingMetricsExample example = new TaggingMetricsExample(meterRegistry);

        // Simulate some operations
        example.simulateLogin();
        example.simulateLogout();
        example.simulateLogin();

        // Print out metrics with tags
        meterRegistry.forEachMeter(meter -> {
            System.out.println("Meter Name: " + meter.getId().getName());
            System.out.println("Tags: " + meter.getId().getTags());
            System.out.println("Measurements: " + meter.measure());
            System.out.println();
        });
    }
}
