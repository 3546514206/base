package com.gqz.thread;

public class Racer implements Runnable {
    private String winner;//胜利者

    public static void main(String[] args) {
        Racer racer = new Racer();
        new Thread(racer, "tortoise").start();
        new Thread(racer, "rabbit").start();
    }

    @Override
    public void run() {
        for (int steps = 1; steps <= 100; steps++) {
            //模拟 兔子休息
            if (Thread.currentThread().getName().equals("rabbit") && steps % 10 == 0) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "--->" + steps);
            //判断比赛是否结束
            boolean flag = gameOver(steps);
            if (flag) {
                break;
            }
        }

    }

    private boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        } else {
            if (steps == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner==>" + winner);
                return true;
            }
        }
        return false;
    }
}
