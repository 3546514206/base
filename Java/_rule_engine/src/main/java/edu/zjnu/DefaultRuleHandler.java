package edu.zjnu;


/**
 * DefaultRuleHandler
 *
 * @Date 2025-04-16 15:37
 * @Author 杨海波
 **/
public class DefaultRuleHandler implements IRuleHandler {
    @Override
    public void handle(RuleTriggerEvent event) {
        System.out.printf("[%s] 触发规则: %s%n",
                event.getTriggerTime(),
                event.getRule().getName());
    }
}
