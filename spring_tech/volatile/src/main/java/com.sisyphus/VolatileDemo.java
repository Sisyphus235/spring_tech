package com.sisyphus;

import java.util.concurrent.TimeUnit;

class TestData {
    volatile int number = 0;

    public void addConstant() {
        this.number = 60;
    }

    public void autoAdd() {
        number++;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        TestData testData = new TestData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    testData.autoAdd();
                }
            }, String.valueOf(i)).start();
        }
        //默认 2 个线程，一个是 main，另一个是 gc，大于 2 说明上面的线程还有在运行中的
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        //如果 volatile 保证原子性，则 number 是 20000，但是 volatile 不保证原子性，所以这个值大概率不等于 20000
        System.out.println(Thread.currentThread().getName() + "\t finally number is " + testData.number);
    }

    public static void testVisibility() {
        TestData testData = new TestData();

        /**
         * 1.验证 volatile 的可见性
         */
        //启动新的线程 A，修改 number 的值
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t start");
            try {
                TimeUnit.SECONDS.sleep(3);
                testData.addConstant();
                System.out.println(Thread.currentThread().getName() + "\t updated number to " + testData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread A").start();

        while (testData.number == 0) {
            // main 线程等待，直到感知到 number 被修改才会退出循环
            // 如果 number 无 volatile 修饰，则不会退出循环，无法结束程序
            // 如果 number 被 volatile 修饰，则 main 线程可见 number 改变，会退出循环
        }
        System.out.println(Thread.currentThread().getName() + "\t mission completed");
    }
}
