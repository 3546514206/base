package edu.zjnu;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

/**
 * JvmMemoryPools
 *
 * @Date 2025-07-15 08:59
 * @Author 杨海波
 **/
@SuppressWarnings("all")
public class JvmMemoryPools {

    private static final List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();

    // ===== 获取 MemoryPoolMXBean 区对象 =====

    public static MemoryPoolMXBean getEdenSpace() {
        return getFirstMatch("Eden");
    }

    public static MemoryPoolMXBean getSurvivorSpace() {
        return getFirstMatch("Survivor");
    }

    public static MemoryPoolMXBean getOldGen() {
        return getFirstMatch("Old Gen", "Tenured");
    }

    public static MemoryPoolMXBean getMetaspace() {
        return getFirstMatch("Metaspace");
    }

    // ===== 获取 used/max 方法（单位：MB） =====

    public static double getEdenUsedMB() {
        return getUsedMB(getEdenSpace());
    }

    public static double getEdenMaxMB() {
        return getMaxMB(getEdenSpace());
    }

    public static double getSurvivorUsedMB() {
        return getUsedMB(getSurvivorSpace());
    }

    public static double getSurvivorMaxMB() {
        return getMaxMB(getSurvivorSpace());
    }

    public static double getOldUsedMB() {
        return getUsedMB(getOldGen());
    }

    public static double getOldMaxMB() {
        return getMaxMB(getOldGen());
    }

    public static double getMetaspaceUsedMB() {
        return getUsedMB(getMetaspace());
    }

    public static double getMetaspaceMaxMB() {
        return getMaxMB(getMetaspace());
    }

    // ===== 通用方法 =====

    private static double getUsedMB(MemoryPoolMXBean pool) {
        return pool == null ? -1 : pool.getUsage().getUsed() / 1024.0 / 1024;
    }

    private static double getMaxMB(MemoryPoolMXBean pool) {
        long max = pool == null ? -1 : pool.getUsage().getMax();
        return max == -1 ? -1 : max / 1024.0 / 1024;
    }

    private static MemoryPoolMXBean getFirstMatch(String... keywords) {
        for (MemoryPoolMXBean pool : pools) {
            String name = pool.getName();
            for (String key : keywords) {
                if (name.contains(key)) {
                    return pool;
                }
            }
        }
        return null;
    }
}
