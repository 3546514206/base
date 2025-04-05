package com.gqz.syn;

/*
 * 死锁： 过多的同步可能会造成相互不是放资源
 * 从而相互等待
 */
public class DeadLock {
    public static void main(String[] args) {
        Markup mp1 = new Markup(0, "李亦非");
        Markup mp2 = new Markup(2, "陈蓉");
        mp1.start();
        mp2.start();
    }
}

//口红 
class Lipstick {

}


//镜子
class Mirror {

}


//化妆
class Markup extends Thread {
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String girl;

    public Markup(int choice, String girl) {
        this.choice = choice;
        this.girl = girl;
    }

    @Override
    public void run() {
        //化妆
        markup();
    }

    //相互持有对方的锁 可能造成死锁
    private void markup() {
        if (choice == 0) {
            synchronized (lipstick) {//获得口红的锁
                System.out.println(this.girl + "涂口红");
                //一秒钟后想获得镜子
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            synchronized (mirror) {
                System.out.println(this.girl + "照镜子");
            }
        } else {
            synchronized (mirror) {//获得镜子的锁
                System.out.println(this.girl + "照镜子");
                //两秒后想涂口红
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            synchronized (lipstick) {
                System.out.println(this.girl + "涂口红");
            }
        }
    }
}