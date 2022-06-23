package edu.zjnu.base.base.jvm;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-11-30
 **/
public class JavaVMStackSOF {

    private int stackLength = -1;

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + javaVMStackSOF.stackLength);
            throw e;
        }


    }

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

}
