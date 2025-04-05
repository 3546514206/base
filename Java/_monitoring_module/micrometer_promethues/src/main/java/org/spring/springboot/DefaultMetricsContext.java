package org.spring.springboot;

import io.micrometer.core.instrument.Tags;

/**
 * DefaultMetricsContext
 * <p>
 * 用于 DefaultMetricsClient 的一个上下文对象，由于 DefaultMetricsClient 是应用于 rpc 调用
 * flw 执行等特定场景，因此 DefaultMetricsClient 工作的上下文中所需的一些参数是可以固定下来的，相对于 Map
 * 作为 Context 对象，DefaultMetricsContext 可以提供更加明确的语义，还能规避使用不当造成的类型安全问题。
 * <p>
 * 如果用户需要更加灵活的监控场景，那么就应该直接使用 MetricsService
 *
 * @author 杨海波
 */
public class DefaultMetricsContext {

    /**
     */
    private final String bizCode;

    /**
     * 覆盖缺省构造函数，使 bizCode 在创建对象的时候必传，而不是通过 bizCode 的 setter，可以避免误用
     *
     */
    public DefaultMetricsContext(String bizCode) {
        this.bizCode = bizCode;
    }

    /**
     * prometheus 协议中的 tags
     */
    private Tags tags;

    /**
     * 监控开始时间
     */
    private Long startTime;

    public String getBizCode() {
        return bizCode;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
}
