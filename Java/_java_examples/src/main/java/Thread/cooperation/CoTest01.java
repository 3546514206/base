package com.gqz.cooperation;

/*
 * 协作模型：生产者消费者实现方式一 ：管道法
 */
public class CoTest01 {
    public static void main(String[] args) {
        SynContianer container = new SynContianer();
        new Productor(container, "生产者").start();
        new Consumer(container, "消费者").start();
    }
}


//生产者 
class Productor extends Thread {
    SynContianer container;

    public Productor(SynContianer container, String name) {
        super(name);
        this.container = container;
    }

    @Override
    public void run() {
        //生产
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":生产---->" + i + "个馒头");
            container.push(new Steamedbun(i));
        }
    }
}

//消费者
class Consumer extends Thread {
    SynContianer container;

    public Consumer(SynContianer container, String name) {
        super(name);
        this.container = container;
    }

    @Override
    public void run() {
        //消费
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":消费---->" + container.pop().id + "个馒头");
        }
    }
}

//缓冲区
class SynContianer {
    Steamedbun[] buns = new Steamedbun[10];
    int count = 0;//计数器

    //存储 生产
    public synchronized void push(Steamedbun bun) {
        //何时 能生产 容器存在数据

        //不能生产 只有等待
        if (count == buns.length) {
            try {
                this.wait(); //线程阻塞 消费者通知生产者 解除阻塞
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        buns[count] = bun;
        count++;
        //存在数据了，可以消费了
        this.notifyAll();
    }

    //获取 消费
    public synchronized Steamedbun pop() {
        //何时消费 容器中是否存在数据
        //没有数据 只有等待
        if (count == 0) {
            try {
                this.wait(); //线程阻塞  生产者通知消费，解除阻塞
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        count--;
        Steamedbun bun = buns[count];
        this.notifyAll();// 存在空间了，可以唤醒对方
        return bun;
    }
}

//馒头
class Steamedbun {
    int id;

    public Steamedbun(int id) {
        super();
        this.id = id;
    }

}