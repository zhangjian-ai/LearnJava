package com.zhangjian.多线程;

import java.util.concurrent.*;

public class MultiThread {

    public static void main(String... args) throws InterruptedException, ExecutionException {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        // 就干调一下，延迟 3s 执行
        scheduledThreadPool.schedule(new Task(), 3, TimeUnit.SECONDS);

        // 周期执行。首次等待 5s，周期间隔 4s
        scheduledThreadPool.scheduleWithFixedDelay(new Task(), 1, 4, TimeUnit.SECONDS);

        Thread.sleep(10000);

        scheduledThreadPool.shutdown();
        System.out.println("结束咯~");
    }
}

class Task implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 执行了一下子~");
    }
}