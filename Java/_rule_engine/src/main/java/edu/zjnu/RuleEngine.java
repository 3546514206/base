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
        this.rules = rules;
        this.handler = handler;
    }

    public void loadRules(List<Rule> rules) {
        this.rules = rules.stream()
                .sorted(Comparator.comparingInt(Rule::getPriority).reversed())
                .collect(Collectors.toList());
    }

    public void execute(Map<String, Object> inputData) {
        for (Rule rule : rules) {
            if (rule.getStatus() != 1) continue;

            boolean result = evaluateRule(rule, inputData);
            if (result) {
                triggerAction(rule);
                break; // 根据优先级执行，匹配后终止
            }
        }
    }

    private boolean evaluateRule(Rule rule, Map<String, Object> inputData) {
        List<Boolean> conditionResults = new ArrayList<>();
        for (RuleCondition cond : rule.getConditions()) {
            Object actualValue = inputData.get(cond.getField());
            if (actualValue == null) {
                conditionResults.add(false);
                continue;
            }
            boolean matched = evaluateCondition(cond, actualValue);
            conditionResults.add(matched);
        }

        return rule.getCombinationType().equalsIgnoreCase("ALL") ?
                conditionResults.stream().allMatch(b -> b) :
                conditionResults.stream().anyMatch(b -> b);
    }

    private boolean evaluateCondition(RuleCondition cond, Object actualValue) {
        try {
            Operator op = cond.getOperator();
            String expected = cond.getCompareValue();
            switch (op) {
                case EQ:
                    return compareEquals(actualValue, expected);
                case NE:
                    return !compareEquals(actualValue, expected);
                case GT:
                    return compareNumber(actualValue, expected) > 0;
                case LT:
                    return compareNumber(actualValue, expected) < 0;
                case GE:
                    return compareNumber(actualValue, expected) >= 0;
                case LE:
                    return compareNumber(actualValue, expected) <= 0;
                default:
                    return false;
            }
        } catch (Exception e) {
            return false; // 类型转换错误视为不匹配
        }
    }

    private boolean compareEquals(Object actual, String expected) {
        if (actual instanceof Number) {
            BigDecimal actualNum = new BigDecimal(actual.toString());
            BigDecimal expectedNum = new BigDecimal(expected);
            return actualNum.compareTo(expectedNum) == 0;
        }
        return actual.toString().equals(expected);
    }

    private int compareNumber(Object actual, String expected) {
        BigDecimal actualNum = new BigDecimal(actual.toString());
        BigDecimal expectedNum = new BigDecimal(expected);
        return actualNum.compareTo(expectedNum);
    }

    // 用户自定义动作（需实现 IRuleHandler）
    private void triggerAction(Rule rule) {
        handler.handle(rule);
    }
}
