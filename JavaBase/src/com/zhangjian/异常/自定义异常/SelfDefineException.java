package com.zhangjian.异常.自定义异常;

/**
 * throw 和 throws 的区别
 *    1、throws  是一种异常处理的方式；在方法申明的地方使用；后面接 异常的类型
 *    2、throw  是手动引发异常的关键字；在方法体中使用；后面接 异常对象
 *
 * 自定义对象背景
 *    当程序中出现了某些系统没有定义的异常错误，这时候就可以自己定义异常类，用于描述该错误信息
 *
 * 是定义异常的步骤
 *    1、定义异常类，继承 Exception 或 RuntimeException
 *    2、继承 Exception 就属于是 编译异常
 *    3、继承 RuntimeException 则属于 运行异常(一般都继承 RuntimeException ，因为 运行时异常已经默认throws，不需要再额外处理)
 */
public class SelfDefineException {
    // 抑制下告警，看着舒服点
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        int age = 130;

        if (age <= 0 || age > 120){
            throw new AgeException("年龄信息非法，合法范围: 1 ~ 120");
        }
        System.out.println("当前年龄：" + age);
    }
}

// 自定义异常
class AgeException extends RuntimeException{
    // 使用父类的带参构造器，传入异常信息
    public AgeException(String message) {
        super(message);
    }
}