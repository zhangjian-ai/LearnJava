package com.zhangjian.开箱即用.日期时间类;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * 第三代日期类(JDK8 加入)，三个类
 *    LocalDate 只包含日期，可以获取日期字段
 *    LocalTime 只包含时间，可以获取时间字段
 *    LocalDateTime 包含日期和时间，可以获取日期和时间字段
 *
 * 可以将 LocalDateTime 理解为 LocalDate 和 LocalTime 的集合
 *
 * 第三代中 使用 DateTimeFormatter 类进行格式化
 */
public class LocalDateTimeIntroduction {
    public static void main(String[] args) {
        // 创建 LocalDateTime 实例
        LocalDateTime now = LocalDateTime.now(); // 获取当前系统时间的实例
        System.out.println(now);
        
        // 获取单个日期时间信息
        System.out.println("年：" + now.getYear());
        System.out.println("月：" + now.getMonthValue()); // getMonth 返回的是 月份的 英文单词，getMonthValue 返回数字
        System.out.println("日：" + now.getDayOfMonth());
        System.out.println("时：" + now.getHour());
        System.out.println("分：" + now.getMinute());
        System.out.println("秒：" + now.getSecond());

        // 其他常用方法
        LocalDateTime plusDays = now.plusDays(5); // 在当前时间加上5天后的时间，返回的仍然是 LocalDateTime对象
        System.out.println(plusDays);

        LocalDateTime minusWeeks = now.minusWeeks(3); // 在当前减去3周后的时间，返回的仍然是 LocalDateTime对象
        System.out.println(minusWeeks);

        // 时间格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss E");
        String s = formatter.format(plusDays);
        System.out.println(s);

        String s1 = formatter.format(minusWeeks);
        System.out.println(s1);
    }
}
