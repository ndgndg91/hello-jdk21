package com.ndgndg91.mutex;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 뮤텍스는 일반적으로 하나의 스레드만이 임계 영역에 접근할 수 있게 하는 락 메커니즘. ReentrantLock 클래스가 자바에서 뮤텍스의 역할을 수행할 수 있다.
 * 뮤텍스를 소유한 스레드만이 해당 자원을 사용할 수 있고, 다른 스레드는 뮤텍스가 해제될 때까지 대기.
 * 뮤텍스는 소유권 개념을 가지고 있어, 뮤텍스를 잠그는(lock) 스레드만이 해당 뮤텍스를 해제(unlock)할 수 있음.
 * 명시적 락 관리: 스레드가 락을 명시적으로 획득하고 해제해야 한다. lock()과 unlock() 메서드를 사용합니다.
 * 조건 변수 선택: ReentrantLock 을 사용하는 경우, Condition 객체를 생성하여 복잡한 동기화 패턴을 구현할 수 있다.
 * 공정성 설정 가능: ReentrantLock 생성자에서 공정성을 설정할 수 있어, 대기하는 스레드 중 가장 오래 기다린 스레드가 락을 획득할 수 있다.
 */
public class MutexExample implements Runnable {
    private static final Lock lock = new ReentrantLock();

    private static class Task implements Runnable {
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " is working");
                Thread.sleep(1000); // 작업 처리 시간 가정
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread() + " is done");
            }
        }
    }

    public void run() {
        for (int i = 1; i <= 3; i++) {
            Thread vt = Thread.ofVirtual().start(new Task());
            try {
                vt.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
