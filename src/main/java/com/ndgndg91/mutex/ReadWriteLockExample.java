package com.ndgndg91.mutex;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock 인터페이스와 그 구현체인 ReentrantReadWriteLock 을 사용하여
 * 읽기 작업은 여러 스레드가 동시에, 쓰기 작업은 한 번에 하나의 스레드만 수행할 수 있게 한다.
 */
public class ReadWriteLockExample implements Runnable{
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int value = 0;

    public void incrementValue() {
        rwLock.writeLock().lock(); // 쓰기 락
        try {
            value++;
            System.out.println(Thread.currentThread().getName() + "try to increment value to: " + value);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void printValue() {
        rwLock.readLock().lock(); // 읽기 락
        try {
            System.out.println(Thread.currentThread().getName() + " try to get current value: " + value);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void run() {
        var example = new ReadWriteLockExample();

        var writer = Thread.ofVirtual().name("incrementValue").start(example::incrementValue);
        var reader1 = Thread.ofVirtual().name("printValue1").start(example::printValue);
        var reader2 =  Thread.ofVirtual().name("printValue2").start(example::printValue);

        try {
            writer.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

