package com.gqz.cooperation;

/*
 * 协作模式：生产者消费者实现方式：  信号灯法
 */
public class CoTest02 {
    public static void main(String[] args) throws InterruptedException {
        Tv tv = new Tv();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

//生产者 演员
class Player extends Thread {
    Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 1; i < 40; i++) {
            this.tv.play("奇葩说第《" + i + "》部");
        }
    }
}

//消费者 观众
class Watcher extends Thread {
    Tv tv;

    public Watcher(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 1; i < 50; i++) {
            this.tv.watch();
        }
    }
}


//同一个资源 电视
class Tv {
    String voice;
    // 信号灯
    // T true 表示演员表演 观众等待
    // F false 表示观众观看 演员等待
    boolean flag = true;

    // 表演
    public synchronized void play(String voice) {
        // 演员等待
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // 表演
        System.out.println("表演了：" + voice);
        this.voice = voice;

        // 唤醒
        this.notifyAll();
        //切换标志
        this.flag = false;
    }

    // 观看
    public synchronized void watch() {
        // 观众等待
        if (flag) {

            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        // 观看
        System.out.println("观看了：" + voice);
        // 唤醒
        this.notifyAll();
        //切换标志
        this.flag = true;
    }
}