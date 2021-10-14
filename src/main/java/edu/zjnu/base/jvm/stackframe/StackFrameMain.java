package edu.zjnu.base.jvm.stackframe;

/**
 * @description: StackTraceElement
 * @author: 杨海波
 * @date: 2021-10-14
 **/
public class StackFrameMain {

    public static void main(String[] args) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        System.out.println(stack);
    }
}
