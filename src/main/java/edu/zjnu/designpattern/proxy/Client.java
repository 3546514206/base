package edu.zjnu.designpattern.proxy;

/**
 * Create by zhaihongwei on 2018/3/17
 * 客户端对象，访问被代理对象的method()方法前，必须拥有代理对象的权利。
 */
public class Client {

    private Object authority;

    public Client(Object authority) {
        this.authority = authority;
    }

    // 判断是否拥有代理对象的权利
    private boolean haveAuthority() {
        return authority.getClass() == Proxy.class;
    }

    // 调用被代理对象的方法（必须通过代理对象来调用）
    public void getMethod() {

        if (haveAuthority()) {
            Proxy proxy = (Proxy) authority;
            proxy.method();
        } else {
            System.out.println("你没有操作真实对象的权利呦！");
        }
    }
}
