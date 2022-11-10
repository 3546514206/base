package edu.zjnu.base.base;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author: 杨海波
 * @date: 2022-11-10 10:53:13
 * @description: todo
 */
public class LeftSecMain {

    public static void main(String[] args) {

        LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
//        long millSeconds = ChronoUnit.MILLIS.between(LocalDateTime.now(),midnight);
        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
//        System.out.println("当天剩余毫秒3：" + millSeconds);
        System.out.println("当天剩余秒3：" + seconds);

    }
}
