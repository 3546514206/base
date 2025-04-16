package edu.zjnu;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * RuleEngine
 *
 * @Date 2025-04-16 15:27
 * @Author 杨海波
 **/
public class RuleEngine {

    private List<Rule> rules;

    private final IRuleHandler handler;

    public RuleEngine(List<Rule> rules, IRuleHandler handler) {
        this.handler = handler;
        this.rules = rules;
    }

    public void loadRules(List<Rule> rules) {
        this.rules = rules.stream()
                .sorted(Comparator.comparingInt(Rule::getPriority).reversed())
                .peek(rule -> rule.getConditions().sort(Comparator.comparing(RuleCondition::getPriority)))
                .collect(Collectors.toList());
    }


    public void execute(Map<String, Object> inputData) {
        for (Rule rule : this.rules) {
            if (!isRuleActive(rule)) {
                continue;
            }

            boolean rulePassed = evaluateRule(rule, inputData);
            if (!rulePassed) {
                triggerHandler(rule, inputData);
                // 优先级最高规则触发后终止
                break;
            }
        }
    }

    private boolean isRuleActive(Rule rule) {
        return rule.getStatus() != null && rule.getStatus() == 1;
    }


    private boolean evaluateRule(Rule rule, Map<String, Object> inputData) {
        if (rule.getConditions().isEmpty()) {
            return true; // 无条件的规则自动通过
        }

        List<Boolean> conditionResults = new ArrayList<>();
        for (RuleCondition condition : rule.getConditions()) {
            boolean result = evaluateCondition(condition, inputData.get(condition.getField()));
            conditionResults.add(result);
        }

        return determineRuleResult(rule.getCombinationType(), conditionResults);
    }

    private boolean determineRuleResult(String combinationType, List<Boolean> results) {
        switch (combinationType.toUpperCase()) {
            case "ALL":
                return results.stream().allMatch(Boolean::booleanValue);
            case "ANY":
                return results.stream().anyMatch(Boolean::booleanValue);
            default:
                // 未知组合类型视为不通过
                return false;
        }
    }


    private boolean evaluateCondition(RuleCondition condition, Object actualValue) {
        if (actualValue == null) {
            return false;
        }

        try {
            Operator op = condition.getOperator();
            boolean result;
            switch (op) {
                // 暂时没做字符串的比较
                case EQ:
                    result = compareEquals(actualValue, condition.getCompareValue());
                    break;
                case NE:
                    result = !compareEquals(actualValue, condition.getCompareValue());
                    break;
                case GT:
                    result = compareNumber(actualValue, condition.getCompareValue()) > 0;
                    break;
                case LT:
                    result = compareNumber(actualValue, condition.getCompareValue()) < 0;
                    break;
                case GE:
                    result = compareNumber(actualValue, condition.getCompareValue()) >= 0;
                    break;
                case LE:
                    result = compareNumber(actualValue, condition.getCompareValue()) <= 0;
                    break;
                default:
                    throw new IllegalArgumentException("未知的操作符: " + op);
            }
            return result;
        } catch (NumberFormatException e) {
            // 数值转换失败视为不匹配
            return false;
        }
    }

    private boolean compareEquals(Object actual, String expected) {
        if (actual instanceof Number && isNumeric(expected)) {
            BigDecimal a = new BigDecimal(actual.toString());
            BigDecimal b = new BigDecimal(expected);
            return a.compareTo(b) == 0;
        }
        return actual.toString().equals(expected);
    }

    private int compareNumber(Object actual, String expected) {
        BigDecimal a = new BigDecimal(actual.toString());
        BigDecimal b = new BigDecimal(expected);
        return a.compareTo(b);
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private void triggerHandler(Rule rule, Map<String, Object> context) {
        try {
            handler.handle(new RuleTriggerRuleEvent(rule, context));
        } catch (Exception e) {
            System.err.println("规则处理器执行失败: " + e.getMessage());
        }
    }
}
