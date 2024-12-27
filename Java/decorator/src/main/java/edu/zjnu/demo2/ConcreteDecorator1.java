package edu.zjnu.demo2;


/**
 * ConcreteDecorator1 具体装饰器
 *
 * @Date 2024-12-27 10:28
 * @Author 杨海波
 **/
public class ConcreteDecorator1 extends Decorator {

    /**
     * 定义被修饰者
     *
     * @param component
     */
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    /**
     * 定义自己的修饰方法
     */
    public void method1() {
        System.out.println("修饰方法 method1");
    }

    @Override
    public void operator() {
        this.method1();
        super.operator();
    }
}
