package edu.zjnu.designpattern.interpreter.src.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by zhaihongwei on 2018/4/9
 * 环境角色
 */
public class Context {

    // 用来存储终结符的具体值
    public Map<Expression, Integer> map = new HashMap();

    public void addTerminalValue(Expression exp, Integer value) {
        map.put(exp, value);
    }

    public Integer getTerminalValue(Expression exp) {
        return map.get(exp);
    }
}
