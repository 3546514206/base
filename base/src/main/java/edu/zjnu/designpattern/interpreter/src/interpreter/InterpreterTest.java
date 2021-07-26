package edu.zjnu.designpattern.interpreter.src.interpreter;

/**
 * Create by zhaihongwei on 2018/4/9
 */
public class InterpreterTest {

    public static void main(String[] args) {
        // 解释表达式 ： 1 # 3 ，将 # 解释为 + ，加法运算

        // 终结符表达式
        NumberTerminalExpression r1 = new NumberTerminalExpression();
        NumberTerminalExpression r2 = new NumberTerminalExpression();
        // 非终结符表达式
        AddNonTerminalExpression add = new AddNonTerminalExpression(r1, r2);

        // 初始化环境角色，并赋值
        Context context = new Context();
        context.addTerminalValue(r1, 1);
        context.addTerminalValue(r2, 3);

        // 输出解释器的结果
        System.out.println("1 # 3 通过解释器的最终结果为：" + add.interpret(context));

    }
}
