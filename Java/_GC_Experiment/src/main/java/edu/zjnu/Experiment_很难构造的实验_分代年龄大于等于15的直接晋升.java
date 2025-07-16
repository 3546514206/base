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
 * -XX:+UseSerialGC -Xms128m -Xmx128m -Xmn64m -XX:SurvivorRatio=6 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:MaxTenuringThreshold=1
 * <p>
 * <p>
 * 实验三：分代年龄大于等于分代年龄的直接晋升
 * JVM 会根据 Survivor 区的压力，动态降低实际晋升年龄门槛。
 * 如果 Survivor 区压力小，就不会晋升，即使年龄达到 15。
 * 如果 Survivor 区压力太大，又会直接全部挪到老年代而不考虑分代年龄。
 *
 * @Date 2025-07-15 09:43
 * @Author 杨海波
 **/
public class Experiment_很难构造的实验_分代年龄大于等于15的直接晋升 {

    public static void main(String[] args) throws InterruptedException {
        // 创建用于存活的对象（不断进入 Survivor 区）
        List<byte[]> container = new ArrayList<>();

        int loop = 0;

        while (true) {
            double edenUsed = JvmMemoryPools.getEdenUsedMB();
            double edenMax = JvmMemoryPools.getEdenMaxMB();
            double sUsed = JvmMemoryPools.getSurvivorUsedMB();
            double sMax = JvmMemoryPools.getSurvivorMaxMB();
            double oldUser = JvmMemoryPools.getOldUsedMB();
            double oldMax = JvmMemoryPools.getOldMaxMB();

            // 条件断点 edenUsed >= 47
            byte[] data = new byte[1024 * 1024]; // 1MB
            container.add(data);

            // 每 3 次清理一次引用，制造垃圾
            if (++loop % 3 == 0) {
                container.clear();
                System.out.println("Cleared container at loop " + loop);
            }

            double f_oldUser = JvmMemoryPools.getOldUsedMB();
            double f_oldMax = JvmMemoryPools.getOldMaxMB();
            Thread.sleep(500); // 稍作等待，模拟真实应用节奏
        }
    }
}