package edu.zjnu.demo1;


/**
 * Watermark 具体装饰器类，添加水印
 *
 * @Date 2024-12-27 10:20
 * @Author 杨海波
 **/
public class Watermark extends MessageDecorator {

    public Watermark(Message wrapped) {
        super(wrapped);
    }

    @Override
    public String getContent() {
        return wrapped.getContent() + " --Watermarked";
    }
}
