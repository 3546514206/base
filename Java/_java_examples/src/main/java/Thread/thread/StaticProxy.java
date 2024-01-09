package com.gqz.thread;

//接口
interface Marry {
    void happyMarry();
}

/**
 * 静态代理
 * 实现相同接口
 * 1、真实角色
 * 2、代理角色
 *
 * @author ganquanzhong
 * @ClassName: StaticProxy
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月8日 下午1:43:05
 */
public class StaticProxy {
    public static void main(String[] args) {
        new WeddingCompany(new You()).happyMarry();
    }
}

//真实角色
class You implements Marry {
    @Override
    public void happyMarry() {
        System.out.println("终于结婚了。。。。。");

        //静态代理
//		new Thread(实现Runnable接口的类 线程对象 target).start();
    }
}

//代理角色
class WeddingCompany implements Marry {
    //真实角色
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        ready();
        this.target.happyMarry();
        after();
    }

    private void ready() {
        System.out.println("准备婚礼事宜。。。。。。。。");
    }

    private void after() {
        System.out.println("闹洞房！！！");
    }

}
