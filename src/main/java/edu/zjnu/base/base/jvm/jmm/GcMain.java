package edu.zjnu.base.base.jvm.jmm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 基于 JDK8 与 visualVM 进行如下试验追踪 GC 过程
 * -Xmx60m -Xms60m -Xss160k -XX:MaxTenuringThreshold=6  (分代晋升年龄)
 * <p>
 * 当前实验数据：
 * ^/-----------Eden---------/---------S0-------/-------s1--------/^/--------------Old-----------/^
 *            38Mb                   6Mb              6Mb                        50Mb
 *
 * @author: 杨海波
 * @date: 2022-07-16 11:37
 **/
public class GcMain {

    public static void main(String[] args) {
        List<byte[]> container = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int size = scanner.nextInt();
            // size == 1         1b
            // size == 2014      1Kb
            // size == 4056196   1Mb
            container.add(new byte[size]);
        }
    }
}
