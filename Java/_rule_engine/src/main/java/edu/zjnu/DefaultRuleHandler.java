package edu.zjnu;


/**
 * DefaultRuleHandler
 *
 * @Date 2025-04-16 15:37
 * @Author 杨海波
 **/
public class DefaultRuleHandler implements IRuleHandler{
    @Override
    public void handle(Rule rule) {
        System.out.println("Rule triggered: " + rule.getName());
    }
}
