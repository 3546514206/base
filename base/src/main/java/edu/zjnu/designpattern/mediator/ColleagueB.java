package edu.zjnu.designpattern.mediator;

/**
 * Create by zhaihongwei on 2018/4/4
 */
public class ColleagueB extends Colleague {

    /**
     * 通过构造方法接收中介者对象
     *
     * @param mediator
     */
    public ColleagueB(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("ColleagueB获得消息：" + message);
    }

    @Override
    public void sendMessage(String message) {
        mediator.sendMessage("ColleagueB发送的消息--->>>" + message, this);
    }
}
