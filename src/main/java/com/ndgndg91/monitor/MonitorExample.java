package com.ndgndg91.monitor;

public class MonitorExample implements Runnable {
    private static int count = 0;

    public static synchronized void increment() {
        count++;
    }

    public void run() {
        var vt1 = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
                System.out.println(Thread.currentThread() + "vt1 increment");
            }
        });

        var vt2 = Thread.ofVirtual().start(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
                System.out.println(Thread.currentThread() + "vt2 increment");
            }
        });

        try {
            vt1.join();
            vt2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Count: " + count);
    }
}
