package edu.zjnu.designpattern.flyweight;

/**
 * Create by zhaihongwei on 2018/3/22
 */
public class ConcreteFlyweight implements Flyweight {

    private String internalState;

    /**
     * 构造函数的方式传入内部状态
     *
     * @param internalState 内部状态
     */
    public ConcreteFlyweight(String internalState) {
        this.internalState = internalState;
    }

    /**
     * 外部状态通过普通方法参数的形式传入
     * 从而改变具体方法的行为，但是不改变内部状态
     *
     * @param externalState 外部状态
     */
    @Override
    public void operation(String externalState) {
        System.out.println("internalState:" + internalState);
        System.out.println("externalState:" + externalState);
    }
}
