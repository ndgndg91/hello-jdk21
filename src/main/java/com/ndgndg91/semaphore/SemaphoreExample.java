package com.ndgndg91.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample implements Runnable {
    private static final Semaphore semaphore = new Semaphore(3); // 동시에 3개의 스레드만 접근 허용

    private static class Task implements Runnable {
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread() + " is working");
                Thread.sleep(1000); // 작업 처리 시간 가정
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
                System.out.println(Thread.currentThread() + " is done");
            }
        }
    }

    public void run() {

        for (int i = 1; i <= 6; i++) {
            Thread vt = Thread.ofVirtual().start(new Task());
            try {
                vt.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
