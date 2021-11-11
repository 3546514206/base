package edu.zjnu.designpattern.zhaihongwei.interpreter.src.interpreter;

/**
 * Create by zhaihongwei on 2018/4/9
 * 抽象的解释器对象
 */
public interface Expression {

    /**
     * 定义解释器的接口
     */
    Integer interpret(Context context);
}
