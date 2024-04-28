package com.ndgndg91.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample implements Runnable {
    private final int threadCount = 3;
    private final CountDownLatch latch = new CountDownLatch(threadCount);

    public void run() {
        for (int i = 0; i < threadCount; i++) {
            Thread.ofVirtual().start(() -> {
                try {
                    System.out.println(Thread.currentThread() + " is working");
                    Thread.sleep(1000); // 작업 처리 시간 가정
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                    System.out.println(Thread.currentThread() + " has finished");
                }
            });
        }

        try {
            latch.await();  // 모든 스레드가 완료될 때까지 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("All tasks are completed.");
    }
}
