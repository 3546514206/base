package com.gqz.proxy.staticProxy;

/**
 * @author ganquanzhong
 * @ClassName: ProxyStar
 * @Description: 代理人
 * @date 2019年7月22日 下午3:35:54
 */
public class ProxyStar implements Star {

    //代理对象
    private Star star;

    //构造器传入真实对象
    public ProxyStar(Star star) {
        super();
        this.star = star;
    }

    @Override
    public void bookTicket() {
        System.out.println("ProxyStar.bookTicket()");
    }

    @Override
    public void collectMoney() {
        System.out.println("ProxyStar.collectMoney()");
    }

    @Override
    public void confer() {
        System.out.println("ProxyStar.confer()");
    }

    @Override
    public void signContract() {
        System.out.println("ProxyStar.signContract()");
    }

    @Override
    public void sing() {
        star.sing(); //代理需要用到真实对象时，调用真实对象方法
    }

}
