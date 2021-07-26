package edu.zjnu.designpattern.mediator;

/**
 * Create by zhaihongwei on 2018/4/4
 * 抽象的中介者对象
 */
public interface Mediator {

    /**
     * 给具体Colleague发送消息
     *
     * @param message
     * @param colleague
     */
    void sendMessage(String message, Colleague colleague);
}
