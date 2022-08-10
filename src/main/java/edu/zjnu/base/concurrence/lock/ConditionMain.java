package edu.zjnu.base.concurrence.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ConditionMain
 * @author: 杨海波
 * @date: 2022-08-10 14:42
 **/
public class ConditionMain {

    public Lock lock =new ReentrantLock();
    public Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ConditionMain useCase =new ConditionMain();
        ExecutorService executorService = Executors.newFixedThreadPool (2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                useCase.conditionWait();
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                useCase.conditionSignal();
            }
        });
    }

    public void conditionWait()  {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() +"拿到锁了");
            System.out.println(Thread.currentThread().getName() +"等待信号");
            condition.await();
            System.out.println(Thread.currentThread().getName() +"拿到信号");
        }catch (Exception ignored){

        }finally {
            lock.unlock();
        }
    }
    public void conditionSignal() {
        lock.lock();
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() +"拿到锁了");
            condition.signal();
            System.out.println(Thread.currentThread().getName() +"发出信号");
        }catch (Exception ignored){

        }finally {
            lock.unlock();
        }
    }
}
