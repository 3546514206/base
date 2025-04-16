package edu.zjnu;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main
 *
 * @Date 2025-04-16 15:30
 * @Author 杨海波
 **/
public class Main {
    public static void main(String[] args) {
        // 初始化数据
        List<Rule> rules = new ArrayList<>();
        Rule rule1 = new Rule();
        rule1.setId(1L);
        rule1.setCode("sms227-0001");
        rule1.setName("短信频次校验规则");
        rule1.setDescription("触发任意规则，则校验不通过");
        rule1.setStatus(1);
        rule1.setCombinationType("ANY");
        rule1.setPriority(10);

        List<RuleCondition> conditions = new ArrayList<>();
        RuleCondition cond1 = new RuleCondition();
        cond1.setCode("sms227-0001-001");
        cond1.setField("session");
        cond1.setOperator(Operator.LT);
        cond1.setCompareValue("30");
        conditions.add(cond1);

        RuleCondition cond2 = new RuleCondition();
        cond2.setField("minus");
        cond2.setOperator(Operator.LT);
        cond2.setCompareValue("3");
        conditions.add(cond2);

        RuleCondition cond3 = new RuleCondition();
        cond3.setField("day");
        cond3.setOperator(Operator.LT);
        cond3.setCompareValue("60");
        conditions.add(cond3);


        rule1.setConditions(conditions);
        rules.add(rule1);

        // 运行引擎
        RuleEngine engine = new RuleEngine(rules, new DefaultRuleHandler());

        Map<String, Object> data = new HashMap<>();
        data.put("session", "30");
        data.put("minute", "3");
        data.put("day", "60");
        engine.execute(data);
    }
}
