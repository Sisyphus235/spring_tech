package com.sisyphus;

public class SingletonDemo {
    private static SingletonDemo instance = null;
    private static volatile SingletonDemo safeInstance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + " SingletonDemo 初始化");
    }

    // 并发线程无法实现单例模式
    public static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    // Double Check Lock机制
    // 当某个线程执行到第一次检测:
    // 1.读取到 instance 不为 null的时候：
    // 2.instance 的引用对象可能没有完成初始化。
    // 这 2 种情况会出现错误，因为 DCL 是三步完成的：
    // 1.分配对象内存空间 memory = allocate()
    // 2.初始化对象 instance(memory)
    // 3.设置 instance 指向刚分配的内存地址 instance = memory
    // DCL 在出现指令重排的时候可能出错，正常顺序是 1-2-3，当 1-3-2 的时候引发上述两种情况的错误
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

    // 禁止指令重排的 DCL 机制保证单例模式
    public static SingletonDemo getSafeInstance() {
        if (safeInstance == null) {
            synchronized (SingletonDemo.class) {
                if (safeInstance == null) {
                    safeInstance = new SingletonDemo();
                }
            }
        }
        return safeInstance;
    }

    public static void main(String[] args) {
        //并发线程无法实现单例模式
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                SingletonDemo.getInstance();
//            }, String.valueOf(i)).start();
//        }
        //DCL 机制实现单例模式，指令重排会出错
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                SingletonDemo.getDCLInstance();
//            }, String.valueOf(i)).start();
//        }
        //DCL + volatile 机制实现单例模式
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getSafeInstance();
            }, String.valueOf(i)).start();
        }
    }
}
