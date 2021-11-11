package edu.zjnu.designpattern.zhaihongwei.interpreter.src.interpreter;

/**
 * Create by zhaihongwei on 2018/4/9
 * 非终结符表达式，说白了就是运算符号，当然你也可以已经定义一种符号来进行某种运算
 * 例如  R1 # R2 ， 将 # 解释为+ - * / 或者其他的运算。
 */
public class AddNonTerminalExpression implements Expression {

    private Expression left;
    private Expression right;

    public AddNonTerminalExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Integer interpret(Context context) {
        return context.getTerminalValue(left) + context.getTerminalValue(right);
    }
}
