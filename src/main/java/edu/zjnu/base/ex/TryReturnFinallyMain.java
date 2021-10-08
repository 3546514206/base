package edu.zjnu.base.ex;

/**
 * @description: TryReturnFinallyMain
 * @author: 杨海波
 * @date: 2021-10-08
 **/
public class TryReturnFinallyMain {


    public static void main(String[] args) {
        System.out.println(new TryReturnFinallyMain().fun1());
        System.out.println(new TryReturnFinallyMain().fun2());
    }

    public int fun1() {
        int i = 100;
        try {
            return i;
        } finally {
            i = 101;
        }

    }

    public int fun2() {
        try {
            return 100;
        } finally {
            return 101;
        }

    }
}
