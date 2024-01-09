package com.gqz.adapter;

/**
 * 适配器    使用组合   (对象适配器方式,使用了组合的方式跟被适配对象整合)
 * (相当于usb和ps/2的转接器)
 *
 * @author ganquanzhong
 */
public class Adapter2 implements Target {

    private Adaptee adaptee;

    //通过构造器传入
    public Adapter2(Adaptee adaptee) {
        super();
        this.adaptee = adaptee;
    }

    @Override
    public void handleReq() {
        adaptee.request();
    }
}
