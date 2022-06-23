package edu.zjnu;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-06-23 10:16
 **/
public class TargetVM {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        System.out.println("this is a JVM");
        // 30秒之后加载类，观察JVM代理的执行情况
        Thread.sleep(30000);
        Class.forName("edu.zjnu.BeLoader");

    }
}
