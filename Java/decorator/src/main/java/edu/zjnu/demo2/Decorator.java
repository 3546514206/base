package edu.zjnu.demo2;


/**
 * Decorator
 *
 * @Date 2024-12-27 10:26
 * @Author 杨海波
 **/
public abstract class Decorator implements Component {

    private final Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    /**
     * 委托给被修饰者执行
     */
    @Override
    public void operator() {
        this.component.operator();
    }
}
