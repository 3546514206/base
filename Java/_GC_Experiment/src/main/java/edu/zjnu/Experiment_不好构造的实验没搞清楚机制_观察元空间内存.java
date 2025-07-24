package edu.zjnu;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 实验5：观察 Metaspace 的内存使用增长情况
 * <p>
 * 启动参数：
 * -XX:+UseSerialGC -Xms2048m -Xmx2048m -XX:MetaspaceSize=1024m -XX:MaxMetaspaceSize=1024m -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 *
 * @Date 2025-07-23
 * @Author 杨海波
 */
public class Experiment_不好构造的实验没搞清楚机制_观察元空间内存 {

    // 自定义类加载器
    static class MyClassLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] bytes) {
            return super.defineClass(name, bytes, 0, bytes.length);
        }
    }

    // 返回一个简单类的字节码（动态生成一个类）
    public static byte[] generateClass(String className) {
        return new byte[]{
                (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE,
                0x00, 0x00, 0x00, 0x34  // 简化：这不是一个有效类，只是模拟用
        };
        // 实际实验中你可使用 ASM 或 javassist 动态生成真实 class
    }

    public static void main(String[] args) throws Exception {
        List<WeakReference<Class<?>>> classRefs = new ArrayList<>();

        MemoryPoolMXBean metaspacePool = ManagementFactory.getMemoryPoolMXBeans().stream()
                .filter(mb -> mb.getName().contains("Metaspace"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Metaspace not found"));

        int count = 0;

        while (true) {
            // 模拟每轮加载10个类
            for (int i = 0; i < 10; i++) {
                String className = "com.example.Generated" + count;

                byte[] classBytes = generateClass(className);

                MyClassLoader loader = new MyClassLoader();
                Class<?> clazz = loader.defineClass(className, classBytes);

                // 用弱引用包裹，防止 GC 阻止卸载
                classRefs.add(new WeakReference<>(clazz));

                count++;
            }

            // 每100轮打印内存信息
            if (count % 100 == 0) {
                long used = metaspacePool.getUsage().getUsed();
                long committed = metaspacePool.getUsage().getCommitted();
                System.out.printf("===> class count: %d, metaspace used: %d KB, committed: %d KB%n",
                        count, used / 1024, committed / 1024);
            }

            // 每 2000 个类尝试清理
            if (count % 2000 == 0) {
                System.out.println(">>> Suggesting GC");
                classRefs.clear();   // 清除引用
                System.gc();         // 显式 GC
                Thread.sleep(200);   // 等待 GC
            }

            Thread.sleep(20); // 控制节奏，避免过快触发 OOM
        }
    }
}
