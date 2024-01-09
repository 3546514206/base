package com.gqz.syn;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发容器
 *
 * @author ganquanzhong
 * @ClassName: SynContainer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年7月9日 下午2:57:55
 */
public class SynContainer {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

        for (int i = 0; i < 10000; i++) {
            list.add(Thread.currentThread().getName());
        }
//		Thread.sleep(5000);
        System.out.println(list.size());
    }
}
