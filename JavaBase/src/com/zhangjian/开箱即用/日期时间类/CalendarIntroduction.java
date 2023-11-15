package com.zhangjian.开箱即用.日期时间类;

import java.util.Calendar;

/**
 * 第二代日期类
 *    public abstract class Calendar implements Serializable, Cloneable, Comparable<Calendar>
 *
 *    Calendar 是一个抽象类，不能直接实例化；
 *    Calendar 没有提供时间格式化的方法，需要自定义拼接；
 *    Calendar 为特定瞬间与一组诸如 YEAR、MONTH、DAY_OF_MONTH、HOUR 等日历字段提供了转换方法；
 */
public class CalendarIntroduction {
    public static void main(String[] args) {
        // 演示部分使用方法
        Calendar instance = Calendar.getInstance();
        System.out.println(instance);

        System.out.println("年：" + instance.get(Calendar.YEAR));
        System.out.println("月：" + instance.get(Calendar.MONTH));
        System.out.println("日：" + (instance.get(Calendar.DAY_OF_MONTH) + 1)); // Calendar 返回的月份是从0开始的，所以要 +1
        System.out.println("时：" + instance.get(Calendar.HOUR)); // 12 小时制
        System.out.println("时：" + instance.get(Calendar.HOUR_OF_DAY)); // 24 小时制
        System.out.println("分：" + instance.get(Calendar.MINUTE));
        System.out.println("秒：" + instance.get(Calendar.SECOND));

        // 自定义格式输出
        System.out.println(instance.get(Calendar.YEAR) + "-" + instance.get(Calendar.MONTH) + "-" + instance.get(Calendar.DAY_OF_MONTH)
        + " " + instance.get(Calendar.HOUR_OF_DAY) + ":" + instance.get(Calendar.MINUTE) + ":" + instance.get(Calendar.SECOND));
    }
}
