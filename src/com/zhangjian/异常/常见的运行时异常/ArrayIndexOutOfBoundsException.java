package com.zhangjian.异常.常见的运行时异常;

/**
 * ArrayIndexOutOfBoundsException 数组下标越界异常
 *    用非法的索引访问数组时抛出的异常。比如：索引为负数、索引大于等于数组的大小，这时索引就是非法索引
 */
public class ArrayIndexOutOfBoundsException {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        // 合法的索引有 0 1 2 3
        System.out.println(nums[3]);

        // 下面两个都是非法索引
//        System.out.println(nums[-1]);
        System.out.println(nums[4]);
    }
}
