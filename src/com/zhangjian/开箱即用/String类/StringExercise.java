package com.zhangjian.开箱即用.String类;

/**
 * 练习题：
 *   将字符转钟指定范围的字符串进行翻转。
 *
 *   如：abcdefg  将索引1到索引6(包含)之间的字符串进行翻转 -> agfedcb
 */
public class StringExercise {
    public static void main(String[] args) {
        reverse("abcdefg", 1, 6);
    }

    public static void reverse(String source, int start, int end){
        // 索引合法性检查
        if (start < 0 || start >= source.length() || end >= source.length() || end - start <= 1){
            System.out.println("起止索引无效");
            return;
        }

        // 转换为数组
        char[] chars = source.toCharArray();

        // 字符翻转
        char temp;
        for (; start < end; start++, end--) {
            temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
        }

        // 再将字符数组转为String
        String target = String.valueOf(chars);
        System.out.println(target);
    }
}
