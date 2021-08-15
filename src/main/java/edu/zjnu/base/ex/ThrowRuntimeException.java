package edu.zjnu.base.ex;

/**
 * @description: 抛出一个运行时异常
 * @author: 杨海波
 * @date: 2021-07-11
 **/
public class ThrowRuntimeException {

    public static void main(String[] args) throws Exception {
        fun();
        fun1();
    }

    /**
     * 抛出一个checked exception，必须被处理或者往上抛（总有一个地方被处理），编译期间就会议别检测出来有没有被处理
     *
     * @throws Exception
     */
    private static void fun1() throws Exception {
        throw new Exception("抛出一个checked exception");
    }

    /**
     * 抛出一个runtime exception，编译期间不会检查改异常是否被处理，所以可以不必处理。但是运行时抛出就会导致程序无法继续运行下去
     */
    private static void fun() {
        throw new IllegalArgumentException("抛出一个runtime exception");
    }
}
