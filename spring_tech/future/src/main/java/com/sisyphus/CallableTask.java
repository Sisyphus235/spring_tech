package com.sisyphus;

import java.util.Date;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {
    Integer i;

    public CallableTask(Integer i) {
        super();
        this.i = i;
    }

    public Integer call() throws Exception {
        if (i == 1) {
            Thread.sleep(3000);
        } else if (i == 5) {
            Thread.sleep(5000);
        } else {
            Thread.sleep(1000);
        }
        System.out.println("task 线程" + Thread.currentThread().getName() + "任务i=" + i + "完成" + new Date());

        return i;
    }
}
