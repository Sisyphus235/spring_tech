package com.sisyphus;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        //开启多线程
        ExecutorService exs = Executors.newFixedThreadPool(10);
        try {
            //结果集
            List<Integer> list = new ArrayList<Integer>();
            List<FutureTask<Integer>> futureList = new ArrayList<FutureTask<Integer>>();
            //启动线程池，10 个任务固定线程数为 5
            for(int i = 0; i < 10; i++) {
                FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableTask(i+1));
                //提交任务，添加返回，Runnable 特性
                exs.submit(futureTask);
                //Future 特性
                futureList.add(futureTask);
            }
            Long getResultStart = System.currentTimeMillis();
            System.out.println("结果归集开始时间=" + new Date());
            //结果归集
            while(futureList.size() > 0) {
                Iterator<FutureTask<Integer>> iterable = futureList.iterator();
                while(iterable.hasNext()) {
                    Future<Integer> future = iterable.next();
                    if (future.isDone() && !future.isCancelled()) {
                        Integer i = future.get();
                        System.out.println("任务i=" + i + "获取完成，移除任务队列" + new Date());
                        list.add(i);
                        iterable.remove();
                    } else {
                        Thread.sleep(1);
                    }
                }
            }

            System.out.println("list=" + list);
            System.out.println("总耗时=" + (System.currentTimeMillis()-start) + ",取结果集耗时=" +
                    (System.currentTimeMillis()-getResultStart));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();
        }
    }
}
