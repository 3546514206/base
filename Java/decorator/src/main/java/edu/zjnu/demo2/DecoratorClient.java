package edu.zjnu.demo2;


/**
 * DecoratorClient 测试类
 *
 * @Date 2024-12-27 10:31
 * @Author 杨海波
 **/
public class DecoratorClient {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        // 第一次修饰
        component = new ConcreteDecorator1(component);
        // 第二次修饰
        component = new ConcreteDecorator2(component);
        // 修饰后运行
        component.operator();
    }
}
