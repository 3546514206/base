package edu.zjnu;


import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * RuleTriggerRuleEvent
 *
 * @Date 2025-04-16 16:59
 * @Author 杨海波
 **/
public class RuleTriggerRuleEvent implements IRuleEvent {

    private final Rule rule;

    private final Map<String, Object> context;

    private final Date triggerTime = new Date();

    public RuleTriggerRuleEvent(Rule rule, Map<String, Object> context) {
        this.rule = rule;
        this.context = Collections.unmodifiableMap(context);
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    @Override
    public Rule getEventSource() {
        return rule;
    }
}
