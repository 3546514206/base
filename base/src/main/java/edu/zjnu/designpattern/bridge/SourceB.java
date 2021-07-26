package edu.zjnu.designpattern.bridge;

/**
 * Create by zhaihongwei on 2018/3/20
 */
public class SourceB implements Component {

    @Override
    public void method() {
        System.out.println("这是SourceB 的具体实现方法");
    }
}
