import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTaskExample
 *
 * @Date 2024-10-13 13:42
 * @Author 杨海波
 **/
public class FutureTaskExample {

    public static void main(String[] args) {
        // 创建一个 Callable 对象，它的 call 方法将返回一个结果
        Callable<Integer> callableTask = () -> {
            System.out.println("Task is being executed...");
            // 模拟耗时任务
            Thread.sleep(2000);
            return 42; // 返回结果
        };

        // 将 Callable 封装为 FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(callableTask);

        // 创建一个线程来执行任务
        Thread taskThread = new Thread(futureTask);
        taskThread.start();

        try {
            // 可以做其他事情...
            System.out.println("Doing something else while waiting for the result...");

            // 获取任务的执行结果（会阻塞直到任务完成）
            // FutureTask 有个问题，就是我在 get 的时候是阻塞的
            Integer result = futureTask.get();
            System.out.println("Task result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}