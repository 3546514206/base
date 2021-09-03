package edu.zjnu.spring.others;

import org.springframework.util.StopWatch;

/**
 * @description: StopWatchMain
 * @author: 杨海波
 * @date: 2021-09-03
 **/
public class StopWatchMain {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("任务一");
        Thread.sleep(700);
        stopWatch.stop();


        stopWatch.start("任务二");
        Thread.sleep(300);
        stopWatch.stop();

        String rs = stopWatch.prettyPrint();
        System.out.println(rs);
    }
}
