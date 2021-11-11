package edu.zjnu.designpattern.zhaihongwei.mediator;

/**
 * Create by zhaihongwei on 2018/4/4
 * 抽象同事类
 */
public abstract class Colleague {

    public Mediator mediator;

    /**
     * 通过构造方法接收中介者对象
     *
     * @param mediator
     */
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    /**
     * 接受其他Colleague对象发送的消息
     *
     * @param message
     */
    public abstract void getMessage(String message);

    /**
     * 发送消息，给其他的Colleague对象
     *
     * @param message
     */
    public abstract void sendMessage(String message);
}
