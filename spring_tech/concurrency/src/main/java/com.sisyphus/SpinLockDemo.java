package com.sisyphus;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    private AtomicReference<Thread> spinLock = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t invoked lock.");
        while (!spinLock.compareAndSet(null, thread)) {
            // 等待自旋锁释放，获得
        }
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        spinLock.compareAndSet(thread, null);
        System.out.println(thread.getName() + "\t invoked unlock.");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "A").start();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "B").start();
    }
}
