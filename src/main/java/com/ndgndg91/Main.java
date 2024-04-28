package com.ndgndg91;

import com.ndgndg91.countdownlatch.CountDownLatchExample;
import com.ndgndg91.cyclicbarrier.CyclicBarrierExample;
import com.ndgndg91.monitor.MonitorExample;
import com.ndgndg91.mutex.ConditionVariableExample;
import com.ndgndg91.mutex.MutexExample;
import com.ndgndg91.mutex.ReadWriteLockExample;
import com.ndgndg91.semaphore.SemaphoreExample;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        semaphoreExample();
        mutexExample();
        monitorExample();
        countDownLatchExample();
        cyclicBarrierExample();
        conditionVariableExample();
        readWriteLockExample();
    }

    private static void semaphoreExample() throws InterruptedException {
        var semaphoreVT = Thread.ofVirtual().name("semaphoreExample").start(new SemaphoreExample());
        semaphoreVT.join();
    }

    private static void mutexExample() throws InterruptedException {
        var mutexVT = Thread.ofVirtual().name("mutexExample").start(new MutexExample());
        mutexVT.join();
    }

    private static void monitorExample() throws InterruptedException {
        var monitorVT = Thread.ofVirtual().name("monitorExample").start(new MonitorExample());
        monitorVT.join();
    }

    private static void countDownLatchExample() throws InterruptedException {
        var countDownLatchVT = Thread.ofVirtual().name("countDownLatchExample").start(new CountDownLatchExample());
        countDownLatchVT.join();
    }

    private static void cyclicBarrierExample() throws InterruptedException {
        var cycleBarrierVT = Thread.ofVirtual().name("cyclicBarrierExample").start(new CyclicBarrierExample());
        cycleBarrierVT.join();
    }

    private static void conditionVariableExample() throws InterruptedException {
        var conditionVariableVT = Thread.ofVirtual().name("conditionVariableExample").start(new ConditionVariableExample());
        conditionVariableVT.join();
    }

    private static void readWriteLockExample() throws InterruptedException {
        var readWriteLockVT = Thread.ofVirtual().name("readWriteLockExample").start(new ReadWriteLockExample());
        readWriteLockVT.join();
    }
}
