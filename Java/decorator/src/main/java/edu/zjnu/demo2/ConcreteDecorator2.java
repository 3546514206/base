package edu.zjnu.demo2;


/**
 * ConcreteDecorator2 具体装饰器
 *
 * @Date 2024-12-27 10:29
 * @Author 杨海波
 **/
public class ConcreteDecorator2 extends Decorator {

    /**
     * 定义被修饰者
     *
     * @param component
     */
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    /**
     * 定义自己的修饰方法
     */
    public void method2() {
        System.out.println("修饰方法 method2");
    }

    @Override
    public void operator() {
        super.operator();
        this.method2();
    }
}