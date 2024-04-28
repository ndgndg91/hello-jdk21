package com.ndgndg91.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample implements Runnable{
    private final int partyCount = 3;
    private final CyclicBarrier barrier = new CyclicBarrier(partyCount, () -> System.out.println("Barrier reached, continuing"));
    public void run() {
        for (int i = 0; i < partyCount; i++) {
            Thread.ofVirtual().start(() -> {
                try {
                    System.out.println(Thread.currentThread() + " is waiting at the barrier");
                    barrier.await();  // 대기
                    System.out.println(Thread.currentThread() + " has crossed the barrier");
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
}
