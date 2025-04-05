package com.gqz.syn;

/**
 * @author ganquanzhong
 * @ClassName: HappyCinema
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月9日 下午1:47:33
 */
public class HappyCinema {
    public static void main(String[] args) {
        Cinmea cinmea = new Cinmea(6, "快乐影院");

        new Thread(new Customer(cinmea, 3), "老高").start();
        new Thread(new Customer(cinmea, 4), "gqz").start();
    }
}

//顾客
class Customer implements Runnable {
    Cinmea cinmea;
    int seats;

    public Customer(Cinmea cinmea, int seats) {
        this.cinmea = cinmea;
        this.seats = seats;
    }

    @Override
    public void run() {
        synchronized (cinmea) {
            boolean flag = cinmea.bookTickets(seats);
            if (flag) {
                System.out.println("出票成功！" +
                        Thread.currentThread().getName() + "位置为--->：" + seats);
            } else {
                System.out.println("票数不够！！");
            }
        }
    }
}


//影院
class Cinmea {
    int available;
    String name;

    public Cinmea(int available, String name) {
        this.available = available;
        this.name = name;
    }

    //购票
    public boolean bookTickets(int seats) {
        System.out.println("可用票数为:" + available);
        if (seats > available) {
            return false;
        }
        available -= seats;
        return true;
    }
}


