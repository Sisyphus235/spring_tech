package com.sisyphus;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


public class ContainerNotSafeDemo {

    private static void threadRun(List<String> list) {
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();

        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        threadRun(list);


        /**
         * 故障现象：ConcurrentModificationException
         */
        // 1. 使用集合构建的线程安全工具类
        List<String> list1 = Collections.synchronizedList(new ArrayList<>());
        threadRun(list1);
        //2. 并发包里的写时复制
        List<String> list2 = new CopyOnWriteArrayList<>();
        threadRun(list2);

    }
}
