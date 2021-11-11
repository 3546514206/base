package edu.zjnu.designpattern.zhaihongwei.mediator;

/**
 * Create by zhaihongwei on 2018/4/4
 */
public class ColleagueA extends Colleague {

    /**
     * 通过构造方法接收中介者对象
     *
     * @param mediator
     */
    public ColleagueA(Mediator mediator) {
        super(mediator);
    }

    @Override
    /**
     * 具体的Colleague对象给具体的Colleague对象发送消息通过中介者对象来完成
     */
    public void getMessage(String message) {
        System.out.println("ColleagueA获得消息：" + message);
    }

    @Override
    public void sendMessage(String message) {
        mediator.sendMessage("ColleagueA发送的消息--->>>" + message, this);
    }
}
