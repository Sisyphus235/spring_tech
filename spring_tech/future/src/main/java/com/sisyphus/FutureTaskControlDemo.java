package com.sisyphus;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTaskControlDemo {

    public static void main(String[] args) {
        try {
            System.out.println("举例统计公司盈利是否到达目标 100万");
            //利润
            Integer count = 0;
            //1.定义 FutureTask，假设远程获取各个分公司业绩
            FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableTask());
            Thread futureTaskThread = new Thread(futureTask);
            futureTaskThread.start();
            System.out.println("futureTaskThread start, " + new Date());

            //2.主线程先做其他工作
            System.out.println("主线程查询总部公司利润开始，" + new Date());
            Thread.sleep(5000);
            count += 10;
            System.out.println("主线程查询总部公司利润结果，" + new Date());

            //利润达标 100 万，则不再执行分公司业绩查询任务
            if(count >= 100) {
                System.out.println("总部利润达标，取消 futureTask" + new Date());
                futureTask.cancel(true);
            } else {
                System.out.println("总部利润未达标，进入阻塞查询分公司利润" + new Date());
                //3.总部未达标，阻塞获取分公司结果
                Integer i = futureTask.get();
                System.out.println("i=" + i + "获取到结果," + new Date());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class CallableTask implements Callable<Integer> {
        public Integer call() throws Exception {
            System.out.println("CallableTask-call， 查询分公司利润，开始" + new Date());
            Thread.sleep(10000);
            System.out.println("CallableTask-call， 查询分公司利润，完毕" + new Date());
            return 10;
        }
    }
}
