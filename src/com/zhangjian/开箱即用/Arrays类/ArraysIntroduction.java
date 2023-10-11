package com.zhangjian.开箱即用.Arrays类;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Arrays 里面包含了一系列静态方法，用于操作或管理数组
 */
public class ArraysIntroduction {
    public static void main(String[] args) {
        // toString 返回数组的字符串形式
        System.out.println("=====toString======");
        Integer[] nums = {3, 4, -1, 17, 34, 9, -8, 0};
        System.out.println(Arrays.toString(nums));

        // copyOf 数组复制，从原数组复制得到一个新的数组对象，并且指定新数组的长度
        System.out.println("=====copyOf======");
        Integer[] nums1 = Arrays.copyOf(nums, 10);
        System.out.println(Arrays.toString(nums1)); // [3, 4, -1, 17, 34, 9, -8, 0, null, null]  两个未赋值的为默认值 null
        System.out.println(nums1.length); // 10

        // sort 数组排序，默认升序
        System.out.println("=====sort======");
        Integer[] n1 = Arrays.copyOf(nums1, 8);
        Arrays.sort(n1);
        System.out.println(Arrays.toString(n1)); // [-8, -1, 0, 3, 4, 9, 17, 34]

        // sort 排序，可自定义排序规则，提供一个 Comparator接口 实现类的对象即可
        // 下面通过 匿名内部类 演示用法
        Integer[] n2 = Arrays.copyOf(nums1, 8);
        Arrays.sort(n2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 -o1; // 改变比较规则，默认是 o1 - o2，底层通过返回值是 正数还是负数 还决定把哪个数往后放
            }
        });
        System.out.println(Arrays.toString(n2)); // [34, 17, 9, 4, 3, 0, -1, -8]

        // sort 还可以指定排序的区间，左开右闭区间，即不包含结束索引的位置
        Integer[] n3 = Arrays.copyOf(nums1, 8);
        Arrays.sort(n3, 2, 7);
        System.out.println(Arrays.toString(n3)); // [3, 4, -8, -1, 9, 17, 34, 0]


        // binarySearch 二分查找，返回目标值在数组的索引
        // 要求进行查找的数组是有序的，且必须为升序
        // 如果查找的目标值不在数组中，那返回值是 -(目标值应该在的位置索引 + 1)
        System.out.println("=====binarySearch======");
        Integer[] n4 = Arrays.copyOf(n1, n1.length);
        System.out.println(Arrays.toString(n4));
        int i = Arrays.binarySearch(n4, 0);
        System.out.println(i); // 2
        System.out.println(Arrays.binarySearch(n4, 15)); // -7


        // fill 数组填充，将数组每个索引处都填上指定值
        System.out.println("=====fill======");
        int[] n5 = new int[10];
        Arrays.fill(n5, 101);
        System.out.println(Arrays.toString(n5));


        // equals 判断两个数组的元素个数、顺序、值 是否都相同，有一个不同都返回false
        System.out.println("=====equals======");
        int[] n6 = {1, 2, 3, 4, 5};
        int[] n7 = {1, 2, 3, 4, 5};
        int[] n8 = {1, 2, 3, 5, 4};
        System.out.println(Arrays.equals(n6, n7)); // true
        System.out.println(Arrays.equals(n6, n8)); // false


        // asList 将 数组(一个或多个) 转换成一个新的 ArrayList 对象返回
        // 原数组不变
        System.out.println("=====asList======");
        List<int[]> list = Arrays.asList(n6, n8);

        list.forEach(new Consumer<int[]>() { // 遍历列表，还是利用 匿名内部类
            @Override
            public void accept(int[] ints) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
            }
        }); // 1 2 3 4 5 1 2 3 5 4

        System.out.println();
        System.out.println(list.getClass()); // class java.util.Arrays$ArrayList
        System.out.println(Arrays.toString(n6)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(n8)); // [1, 2, 3, 5, 4]
    }
}
