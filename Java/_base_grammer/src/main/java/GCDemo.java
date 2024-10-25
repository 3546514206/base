import java.util.ArrayList;
import java.util.List;



/**
 * -Xms512m -Xmx2g -Xloggc:gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseG1GC
 *
 * @Date 2024-10-16 09:07
 * @Author 杨海波
 **/

public class GCDemo {
    static class DummyObject {
        private byte[] memory;

        public DummyObject(int size) {
            this.memory = new byte[size];  // 模拟内存占用
        }

        public byte[] getMemory() {
            return memory;
        }
    }

    public static void main(String[] args) {
        List<DummyObject> objects = new ArrayList<>();
        int counter = 0;

        try {
            while (true) {
                // 每次创建1MB的对象并添加到列表
                objects.add(new DummyObject(1024 * 1024));

                // 每次添加1000个对象后清空列表，触发GC
                if (++counter % 1000 == 0) {
                    objects.clear();
                    System.out.println("List cleared, potential GC soon...");
                }

                // 延迟操作以便更容易观察GC
                Thread.sleep(10);
            }
        } catch (OutOfMemoryError e) {
            System.err.println("Out of memory error: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
