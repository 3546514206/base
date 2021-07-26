package edu.zjnu.designpattern.interpreter.src.interpreter;

/**
 * Create by zhaihongwei on 2018/4/9
 * 终结符表达式，用来解释运算符之外的表达式。
 */
public class NumberTerminalExpression implements Expression {

    @Override
    public Integer interpret(Context context) {
        return context.getTerminalValue(this);
    }
}
