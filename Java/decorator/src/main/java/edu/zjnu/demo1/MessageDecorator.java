package edu.zjnu.demo1;


/**
 * MessageDecorator 装饰器类
 *
 * @Date 2024-12-27 10:17
 * @Author 杨海波
 **/
public abstract class MessageDecorator implements Message {

    protected final Message wrapped;

    public MessageDecorator(Message wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getContent() {
        return wrapped.getContent();
    }
}
