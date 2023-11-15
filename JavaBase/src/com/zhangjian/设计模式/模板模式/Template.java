package com.zhangjian.设计模式.模板模式;

/**
 * 模板模式：
 *   定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模版方法使得子类可以不改变一个算法的结构的情况下能重新定义该算法的某些特定步骤。
 */
public class Template {
    public static void main(String[] args) throws InterruptedException{
        CreateCar createCar = new CreateCar();
        AssembleRobot assembleRobot = new AssembleRobot();

        // 直接使用父类的 run 方法，子类无需关心执行流程
        createCar.run();
        assembleRobot.run();
    }
}

class AssembleRobot extends AbstractTemplate {
    @Override
    public boolean start() {
        System.out.println("加载智能装在程序");
        return true;
    }

    @Override
    public void doSomething() throws InterruptedException {
        System.out.println("机器人组装中...");
        Thread.sleep(2000);
    }

    @Override
    public void stop() {
        System.out.println("全新的Ji气人诞生啦");
    }
}

class CreateCar extends AbstractTemplate{
    @Override
    public boolean start() {
        System.out.println("初始化生产流水线完成");
        return true;
    }

    @Override
    public void doSomething() throws InterruptedException {
        System.out.println("汽车正在生产，请稍后...");
        Thread.sleep(3000);
    }

    @Override
    public void stop() {
        System.out.println("汽车生产完成");
    }
}

abstract class AbstractTemplate{
    // 三个抽象方法，没有具体的实现
    public abstract boolean start();

    public abstract void doSomething() throws InterruptedException;

    public abstract void stop();

    // 在实现方法中，控制方法执行顺序
    public void run() throws InterruptedException{
        if (start()){
            // 统计执行时间
            long start = System.currentTimeMillis();
            doSomething();
            long end = System.currentTimeMillis();

            System.out.println("任务执行完毕，共花费 " + (end - start) + "ms");
        }
        stop();
    }
}