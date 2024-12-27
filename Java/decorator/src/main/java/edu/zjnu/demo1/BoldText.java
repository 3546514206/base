package edu.zjnu.demo1;


/**
 * BoldText 具体装饰器类
 *
 * @Date 2024-12-27 10:19
 * @Author 杨海波
 **/
public class BoldText extends MessageDecorator {

    public BoldText(Message wrapped) {
        super(wrapped);
    }

    @Override
    public String getContent() {
        return "<b>" + wrapped.getContent() + "</b>";
    }
}
