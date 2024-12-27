package edu.zjnu.demo1;


/**
 * HtmlFormatter 具体装饰器
 *
 * @Date 2024-12-27 10:18
 * @Author 杨海波
 **/
public class HtmlFormatter extends MessageDecorator {

    public HtmlFormatter(Message wrapped) {
        super(wrapped);
    }

    @Override
    public String getContent() {
        return "<html>" + wrapped.getContent() + "</html>";
    }
}
