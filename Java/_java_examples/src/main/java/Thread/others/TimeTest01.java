package com.gqz.others;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TimeTest01 {
    public static void main(String[] args) {
        Timer timer = new Timer();
//		timer.schedule(new MyTask(), 2000);//执行一次
//		timer.schedule(new MyTask(), 2000,3000);//循环  在2秒后 每隔3秒执行一次

//		Calendar cal = new GregorianCalendar(2019,7,10,16,14,55);
        System.out.println(new SimpleDateFormat("YYYY-MM-dd HH:MM:ss").format(new Date()));
        timer.schedule(new MyTask(), 1000);
    }

}

class MyTask extends TimerTask {

    @Override
    public void run() {
        System.out.println(new SimpleDateFormat("YYYY-MM-dd HH:MM:ss").format(new Date()));
        for (int i = 0; i < 5; i++) {
            System.out.println("timetask" + i);
        }
    }

}
