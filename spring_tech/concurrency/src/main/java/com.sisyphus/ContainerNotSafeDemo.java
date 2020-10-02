package com.sisyphus;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;


public class ContainerNotSafeDemo {

    private static void threadRun(List<String> list) {
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();

        }
    }

    private static void threadRun(Set<String> set) {
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();

        }
    }

    private static void threadRun(Map<String, String> map) {
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();

        }
    }

    public static void main(String[] args) {
        /**
         * Array List
         */
        List<String> list = new ArrayList<>();
//        threadRun(list);

        // 故障现象：ConcurrentModificationException
        // 1. 使用集合构建的线程安全工具类
        List<String> list1 = Collections.synchronizedList(new ArrayList<>());
//        threadRun(list1);
        //2. 并发包里的写时复制
        List<String> list2 = new CopyOnWriteArrayList<>();
//        threadRun(list2);

        /**
         * Hash Set
         * 底层是 hashmap，set 添加的元素是 hashmap 的 key，value 是一个常量 object 叫 PRESENT
         */
        Set<String> set = new HashSet<>();
//        threadRun(set);
        // 故障现象：ConcurrentModificationException
        // 1. 使用集合构建的线程安全工具类
        Set<String> set1 = Collections.synchronizedSet(new HashSet<>());
//        threadRun(set1);
        //2. 并发包里的写时复制
        Set<String> set2 = new CopyOnWriteArraySet<>();
        threadRun(set2);

        /**
         * Hash Map
         */
        Map<String, String> map = new HashMap<>();
//        threadRun(map);
        // 故障现象：ConcurrentModificationException
        // 1. 使用集合构建的线程安全工具类
        Map<String, String> map1 = Collections.synchronizedMap(new HashMap<>());
        threadRun(map1);
        //2. 并发包
        Map<String, String> map2 = new ConcurrentHashMap<>();
        threadRun(map2);

    }
}
