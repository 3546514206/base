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
 * -XX:PretenureSizeThreshold=3145728  # 这只大对象为3M，4M会直接进入老年代
 * <p>
 * -XX:+UseSerialGC -Xms128m -Xmx128m -Xmn64m -XX:SurvivorRatio=6 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:PretenureSizeThreshold=3145728
 * <p>
 * <p>
 * 实验四：老年代的垃圾回收
 *
 * @Date 2025-07-18 09:14
 * @Author 杨海波
 **/
public class Experiment_04_老年代的垃圾回收 {

    public static void main(String[] args) throws InterruptedException {

        // 创建用于存活的对象（不断进入 Survivor 区）
        List<byte[]> container = new ArrayList<>();
        List<byte[]> big_container = new ArrayList<>();

        int loop = 0;


        while (true) {
            double edenUsed = JvmMemoryPools.getEdenUsedMB();
            double edenMax = JvmMemoryPools.getEdenMaxMB();
            double sUsed = JvmMemoryPools.getSurvivorUsedMB();
            double sMax = JvmMemoryPools.getSurvivorMaxMB();
            double oldUsed = JvmMemoryPools.getOldUsedMB();
            double oldMax = JvmMemoryPools.getOldMaxMB();


            // 条件断点 oldUsed >= 127
            byte[] data = new byte[4 * 1024 * 1024]; // 4MB
            container.add(data);


            // 每 3 次清理一次引用，制造垃圾
            if (++loop % 3 == 0) {
                container.clear();
                System.out.println("Cleared container at loop " + loop);
            }

            double f_edenUsed = JvmMemoryPools.getEdenUsedMB();
            double f_edenMax = JvmMemoryPools.getEdenMaxMB();
            double f_sUsed = JvmMemoryPools.getSurvivorUsedMB();
            double f_sMax = JvmMemoryPools.getSurvivorMaxMB();
            double f_oldUsed = JvmMemoryPools.getOldUsedMB();
            double f_oldMax = JvmMemoryPools.getOldMaxMB();
            Thread.sleep(500); // 稍作等待，模拟真实应用节奏
        }
    }

}
