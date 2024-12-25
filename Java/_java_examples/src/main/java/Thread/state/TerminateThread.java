package com.gqz.state;

/*
 *
 */
public class TerminateThread implements Runnable {

    //加入标识 标记县城提是否可以运行
    private boolean flag = true;
    private String name;


    public TerminateThread(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        TerminateThread tt = new TerminateThread("Thread 终止线程");
        new Thread(tt).start();

        for (int i = 0; i < 100; i++) {
            if (i == 88) {
                tt.terminate();
                System.out.println("tt game over ");
            }
            System.out.println("main--->" + i);

        }
    }

    @Override
    public void run() {
        int i = 0;
        //2、关联标示
        while (flag) {
            System.out.println(name + "----->" + i++);
        }
    }

    //3 提供对外方法改变标识
    public void terminate() {
        this.flag = false;
    }
}
