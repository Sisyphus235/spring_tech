package com.sisyphus;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        //开启多线程
        ExecutorService service = Executors.newFixedThreadPool(10);
        try {
            //结果集
            List<Integer> list = new ArrayList<Integer>();
            List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
            //1.提交 10 个任务，每个任务返回一个 Future 入 list
            for(int i=0; i<10; i++) {
                futureList.add(service.submit(new CallableTask(i+1)));
            }
            Long getResultStart = System.currentTimeMillis();
            System.out.println("结果归集开始时间：" + new Date());
            //2.结果归集，用迭代器遍历 futureList，高速轮询，任务完成就移除
            while(futureList.size() > 0) {
                Iterator<Future<Integer>> iterable = futureList.iterator();
                while(iterable.hasNext()) {
                    Future<Integer> future = iterable.next();
                    //如果任务完成，取出结果，否则判断下一个任务
                    if (future.isDone() && !future.isCancelled()) {
                        //获取结果
                        Integer i = future.get();
                        System.out.println("任务 i=" + i + "获取完成，移出队列" + new Date());
                        list.add(i);
                        iterable.remove();
                    } else {
                        Thread.sleep(1); //避免 CPU 高速运转，中间停顿 1s
                    }
                }
            }
            Long end = System.currentTimeMillis();
            System.out.println("list=" + list);
            System.out.println("总耗时=" + (end - start) + "，取结果集耗时=" + (end - getResultStart));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    static class CallableTask implements Callable<Integer> {
        Integer i;

        public CallableTask(Integer i) {
            super();
            this.i = i;
        }

        public Integer call() throws Exception {
            if (i == 1) {
                Thread.sleep(3000); //任务 1 耗时 3s
            } else if (i == 5) {
                Thread.sleep(5000); //任务 5 耗时 5s
            } else {
                Thread.sleep(1000); //其他任务耗时 1s
            }
            System.out.println("task 线程： " + Thread.currentThread().getName() + "任务 i=" + i + "，完成。" + new Date());
            return i;
        }
    }
}
