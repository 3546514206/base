package edu.zjnu.demo1;


/**
 * Main 测试类
 *
 * @Date 2024-12-27 10:21
 * @Author 杨海波
 **/
public class Main {

    public static void main(String[] args) {
        // 创建一个基本的消息对象
        Message plainMessage = new TextMessage("Hello, World!");
        System.out.println("Original Message: " + plainMessage.getContent());

        // 使用装饰器来添加功能
        Message htmlMessage = new HtmlFormatter(plainMessage);
        System.out.println("HTML Formatted Message: " + htmlMessage.getContent());

        // 组合多个装饰器
        Message boldHtmlMessage = new BoldText(htmlMessage);
        System.out.println("Bold HTML Message: " + boldHtmlMessage.getContent());

        // 添加水印
        Message watermarkedMessage = new Watermark(boldHtmlMessage);
        System.out.println("Watermarked Message: " + watermarkedMessage.getContent());
    }
}
