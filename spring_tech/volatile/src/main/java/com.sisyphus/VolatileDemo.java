package com.sisyphus;

import java.util.concurrent.TimeUnit;

class TestData {
    volatile int number = 0;

    public void addConstant() {
        this.number = 60;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
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
