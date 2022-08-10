package edu.zjnu.base.concurrence.multithread;

public class TwoThread {

    //两个线程交替输出， A1B2C3.....Z26
    public static final int SETPS = 26;

    public static void main(String[] args) {
        Printer p = new Printer();
        ThreadNum tn = new ThreadNum();
        tn.setName("Thread num :");
        tn.setP(p);
        ThreadStr ts = new ThreadStr();
        ts.setName("Thread str :");
        ts.setP(p);
        ts.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tn.start();
    }

    public static class Printer {
        public synchronized void print(String i) {
            try {
                this.notify();
                System.out.println(Thread.currentThread().getName() + " " + i);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadNum extends Thread {
        private int startCharCount = 1;
        private Printer p = new Printer();

        public Printer getP() {
            return p;
        }

        public void setP(Printer p) {
            this.p = p;
        }

        @Override
        public void run() {
            for (int i = 0; i < SETPS; i++) {
                p.print(startCharCount++ + "");
            }
        }
    }

    public static class ThreadStr extends Thread {
        private int startCharCount = 65;
        private Printer p = new Printer();

        public Printer getP() {
            return p;
        }

        public void setP(Printer p) {
            this.p = p;
        }

        @Override
        public void run() {
            for (int i = 0; i < SETPS; i++) {
                p.print((char) startCharCount++ + "");
            }
        }
    }
}