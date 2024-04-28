package com.ndgndg91.mutex;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 특정 조건이 달성되기 전까지 대기하도록 유연하게 사용할 수 있다.
 */
public class ConditionVariableExample implements Runnable {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean flag = false;

    public void waitForCondition() {
        lock.lock();
        try {
            while (!flag) {
                System.out.println(Thread.currentThread().getName() + " is waiting for condition to be true.");
                condition.await();  // 조건이 만족될 때까지 대기
            }
            System.out.println("Condition is true," + Thread.currentThread().getName() + " proceeding...");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void setConditionTrue() {
        lock.lock();
        try {
            flag = true;
            condition.signalAll();  // 조건이 만족됨을 알림
            System.out.println(Thread.currentThread().getName() + " Condition set to true and all waiting threads are notified.");
        } finally {
            lock.unlock();
        }
    }

    public void run(){
        ConditionVariableExample example = new ConditionVariableExample();

        var vt1 = Thread.ofVirtual().name("waitForConditionVT").start(example::waitForCondition);
        try {
            Thread.sleep(2000); // 잠시 대기하여 t1이 먼저 condition.await()에서 대기하게 함
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        var vt2 = Thread.ofVirtual().name("setConditionTrueVT").start(example::setConditionTrue);
        try {
            vt1.join();
            vt2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
