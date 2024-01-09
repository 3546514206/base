package com.oop.design;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Landy on 2019/1/6.
 */
public class FactoryDemo {

    public static void main(String[] args) {
        //create new of
        //工厂模式：抽象工厂+静态工厂，状态和无状态，可变和不可变
        //并不是某个命名，一般通过create new前缀
        //
        //java9
        //Set<String> set = Set.of("String"); //不变
        //可变
        ThreadFactory factory = (runnable) -> new Thread(runnable);

        Thread thread = factory.newThread(() -> {
            System.out.println("Hello World");
        });

        thread.start();

        //Integer -> Number : private final int value;
        //MutableInt -> Number : private int value;
        //不变必然每次需要创建新对象，是否需要则需要讨论
        LocalDateTime time = LocalDateTime.of(2019,1,6,22,29);

    }

}
