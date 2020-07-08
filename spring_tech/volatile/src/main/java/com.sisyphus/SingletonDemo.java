package com.sisyphus;

public class SingletonDemo {
    private static SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + " SingletonDemo 初始化");
    }

    //并发线程无法实现单例模式
    public static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    //Double Check Lock机制
    public static SingletonDemo getDCLInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //并发线程无法实现单例模式
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
        //DCL 机制实现单例模式
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getDCLInstance();
            }, String.valueOf(i)).start();
        }
    }
}
