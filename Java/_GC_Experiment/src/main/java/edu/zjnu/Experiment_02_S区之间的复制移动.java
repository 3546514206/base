package edu.zjnu;


import java.util.ArrayList;
import java.util.List;

/**
 * -XX:+UseSerialGC
 * -Xms128m -Xmx128m           # 固定总堆内存为128MB，防止扩容
 * -Xmn64m                     # 新生代固定为64MB（老年代 = 64MB）
 * -XX:SurvivorRatio=6         # Eden:Survivor = 6:1:1，Eden = 48MB，S0/S1 = 8MB
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * <p>
 * -XX:+UseSerialGC -Xms128m -Xmx128m -Xmn64m -XX:SurvivorRatio=6 -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 * <p>
 * 实验二：S区之间的复制移动
 *
 * @Date 2025-07-15 09:07
 * @Author 杨海波
 **/
@SuppressWarnings("all")
public class Experiment_02_S区之间的复制移动 {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> container = new ArrayList<>();

        int loop = 0;

        while (true) {
            double edenMax = JvmMemoryPools.getEdenMaxMB();
            double edenUsed = JvmMemoryPools.getEdenUsedMB();
            double sUsed = JvmMemoryPools.getSurvivorUsedMB();
            double sMax = JvmMemoryPools.getSurvivorMaxMB();

            // 每次创建 1MB 的对象（短生命周期）
            // 条件断点 edenUsed >= 47
            byte[] data = new byte[1024 * 1024]; // 1MB
            container.add(data);

            // 每 3 次清理一次引用，制造垃圾
            if (++loop % 3 == 0) {
                container.clear();
                System.out.println("Cleared container at loop " + loop);
            }

            Thread.sleep(500); // 稍作等待，模拟真实应用节奏
        }
    }

}
