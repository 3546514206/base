package com.gqz.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ganquanzhong
 * @ClassName: HappyCinema
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月9日 下午1:47:33
 */
public class HappyCinema02 {
    public static void main(String[] args) {
        //初始化影院的座子
        List<Integer> available = new ArrayList<Integer>();
        available.add(1);
        available.add(2);
        available.add(3);
        available.add(4);
        available.add(5);
        available.add(6);
        available.add(7);
        //顾客选位置
        List<Integer> seat1 = new ArrayList<Integer>();
        seat1.add(3);
        seat1.add(4);
        seat1.add(5);

        List<Integer> seat2 = new ArrayList<Integer>();
        seat2.add(1);
        seat2.add(2);
        seat2.add(3);

        List<Integer> seat3 = new ArrayList<Integer>();
        seat3.add(7);


        TCinmea tcinmea = new TCinmea(available, "淘票票");

        new Thread(new HappyCustomer(tcinmea, seat1), "老高").start();

        Thread thread = new Thread(new HappyCustomer(tcinmea, seat2), "小甘");
        thread.setPriority(10);
        thread.start();

        new Thread(new HappyCustomer(tcinmea, seat3), "老王").start();
    }
}

//顾客
class HappyCustomer implements Runnable {
    TCinmea tcinmea;
    List<Integer> seats;

    public HappyCustomer(TCinmea tcinmea, List<Integer> seats) {
        this.tcinmea = tcinmea;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (tcinmea) {
            //模拟网络延时
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            boolean flag = tcinmea.bookTickets(seats);
            if (flag) {
                System.out.println("    出票成功！       " +
                        Thread.currentThread().getName() + "位置为--->：" + seats);
                System.out.println("===================");
            } else {
                System.out.println("    票数不够！ 失败       " +
                        Thread.currentThread().getName() + "位置为--->：" + seats);
                System.out.println("===================");
            }
        }
    }
}


//影院
class TCinmea {
    List<Integer> available;
    String name;

    public TCinmea(List<Integer> available, String name) {
        this.available = available;
        this.name = name;
    }

    //购票
    public boolean bookTickets(List<Integer> seats) {
        System.out.println("可用票数为:" + available);
        List<Integer> copy = new ArrayList<Integer>();
        //复制一份位置，判断当前是否满足购票需求
        copy.addAll(available);
        copy.removeAll(seats);
        if (available.size() - seats.size() != copy.size()) {
            System.out.println("当前位置已被选购！");
            return false;
        }
        //出票成功
        available = copy;
        return true;
    }
}


