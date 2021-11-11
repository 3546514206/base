package edu.zjnu.designpattern.zhaihongwei.proxy;

/**
 * Create by zhaihongwei on 2018/3/17
 * 代理对象，通过代理对象操作被代理对象（真实对象）
 */
public class Proxy implements Component {

    private Source source;

    public Proxy(Source source) {
        this.source = source;
    }

    public Proxy() {
        source = new Source();
    }

    @Override
    public void method() {
        source.method();
    }

}
