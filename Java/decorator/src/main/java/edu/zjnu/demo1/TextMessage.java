package edu.zjnu.demo1;


/**
 * TextMessage 定义了一个基础行为
 *
 * @Date 2024-12-27 10:13
 * @Author 杨海波
 **/
public class TextMessage implements Message {

    private final String content;

    public TextMessage(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
