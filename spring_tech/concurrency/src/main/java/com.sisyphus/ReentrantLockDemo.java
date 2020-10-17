package com.sisyphus;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        method1();
    }

    public void method1() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t invoked method1()");
            method2();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t invoked method2()");
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        // synchronized demo
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();

        // reentrantLock demog
        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();
    }
}
