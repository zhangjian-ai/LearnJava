package com.zhangjian.多线程;

public class MultiThread {
    public static void main(String[] args) throws InterruptedException {
        // 模拟死锁
        Thread thread = new Thread(new DeadLock(true));
        Thread thread2 = new Thread(new DeadLock(false));

        thread.start();
        thread2.start();

        thread.join(2000);

        // 查看状态。两个线程都处于阻塞状态
        System.out.println(thread.getState()); // BLOCKED
        System.out.println(thread2.getState()); // BLOCKED

    }
}


class DeadLock implements Runnable {
    static Object o1 = new Object();
    static Object o2 = new Object();

    private boolean flag;

    public DeadLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " 获得 o1 的锁...");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " 获得 o2 的锁...");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " 获得 o2 的锁...");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " 获得 o1 的锁...");
                }
            }
        }
    }
}
