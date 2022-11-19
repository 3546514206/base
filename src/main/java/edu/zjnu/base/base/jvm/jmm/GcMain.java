package edu.zjnu.base.base.jvm.jmm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 基于 JDK8 与 visualVM 进行如下试验追踪 GC 过程
 *
 * @author: 杨海波
 * @date: 2022-07-16 11:37
 **/
public class GcMain {

    public static void main(String[] args) {
        List<byte[]> container = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入需要创建的对象大小：");
            int size = scanner.nextInt();
            System.out.println("输入了 " + size + " kb 的对象");
            container.add(new byte[size * 1024]);
        }
    }
}
