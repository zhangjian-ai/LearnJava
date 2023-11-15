package com.zhangjian.开箱即用.日期时间类;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * 第一代日期类：
 *    Date: 精确到毫秒，代表特定的瞬间
 *    SimpleDateFormat: 格式和解析日期的类，它允许将 日期 -> 文本 或 文本 -> 日期，或者根据需要进行格式化
 *    Instant 时间戳类
 *
 */
public class DateIntroduction {
    public static void main(String[] args) throws ParseException {
        // 创建时间对象
        Date date = new Date(); // 获取当前系统时间
        System.out.println(date);

        Date date1 = new Date(1673820323); // 根据传入的毫秒数，获取一个时间
        System.out.println(date1);

        Date date2 = new Date(System.currentTimeMillis()); // 与第一个等价
        System.out.println(date2);


        // 使用 SimpleDateFormat
        // 创建了一个格式化对象，入参为想要格式化的一个模板。模板使用的字母是事先约定好的，不能乱写
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss E");
        String fd = format.format(date2); // 讲一个 Date对象转换成 String 输出
        System.out.println(fd);

        // 同样，SimpleDateFormat 也可以将 String 再次转换成一个 Date 对象
        // 注意：转换时 文本的日期格式 必须和 SimpleDateFormat 初始化时指定的 pattern 相匹配，否则会抛出 ParseException
        String s = "2021-11-09 11:42:07 周日";
        Date date3 = format.parse(s); // 日期转换可能异常，throws ParseException 一下
        System.out.println(date3);


        // 使用Instant 获取当前时间戳
        Instant now = Instant.now();
        System.out.println(now);

        Date date4 = Date.from(now); // 将时间戳对象转换为Date对象
        System.out.println(date4);

        Instant instant = date4.toInstant(); // 将Date对象转换为Instant对象
        System.out.println(instant);
    }
}
