import java.util.ArrayList;
import java.util.List;

/**
 * -Xms128m -Xmx256m -XX:+PrintGC
 *
 * @Date 2025-06-13 17:20
 * @Author 杨海波
 **/
public class MemoryTest {

    static class DummyObject {
        private byte[] data = new byte[1024 * 100]; // 100 KB
    }

    public static void main(String[] args) throws InterruptedException {
        List<DummyObject> heapObjects = new ArrayList<>();

        System.out.println("程序启动，准备观察堆和栈的变化...");
        // 留时间连接 VisualVM 或 JConsole
        Thread.sleep(5000);

        for (int i = 0; i < 1000; i++) {
            allocateOnHeap(heapObjects);
            simulateStackCall(10);
            // 每次迭代等待一会
            Thread.sleep(100);
        }

        System.out.println("保留引用，防止GC，请观察堆增长情况...");
        // 观察堆内存变化
        Thread.sleep(10000);

        System.out.println("清空引用，触发GC...");
        heapObjects.clear();
        System.gc();

        // GC 后观察堆下降情况
        Thread.sleep(10000000);
        System.out.println("程序结束。");
    }

    // 模拟堆内存分配
    private static void allocateOnHeap(List<DummyObject> container) {
        DummyObject obj = new DummyObject();
        // 添加到列表防止被立即回收
        container.add(obj);
    }

    // 模拟栈帧增长（递归）
    private static void simulateStackCall(int depth) {
        if (depth == 0) {
            return;
        }
        // 局部变量模拟栈使用
        int localVar = depth;
        simulateStackCall(depth - 1);
    }

}
