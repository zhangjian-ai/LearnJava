package com.zhangjian.开箱即用.System类;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * System 类：
 *    介绍几个常用方法
 */
public class SystemIntroduction {
    public static void main(String[] args) {
        // arraycopy 复制数组元素，Arrays.copyOf 方法底层就是用的这个方法
        System.out.println("=====arraycopy=====");
        int[] src = {1, 2 , 3};
        int[] dest = new int[3];

        System.arraycopy(src, 0, dest, 0, src.length);
        System.out.println(Arrays.toString(dest));

        // currentTimeMillis 返回当前时间到 1970-1-1 0时0分0秒 的 毫秒数
        System.out.println("=====currentTimeMillis=====");
        System.out.println(System.currentTimeMillis());

        // gc 触发垃圾回收，异步操作，不阻塞
        System.out.println("=====gc=====");
        System.gc();

        // System 中 out err in 三个对象，分别与操作系统 标准输出、标准错误输出、标准输入 一一对应
        // System.out.print()  所以本质上我们控制台打印，就是把文本信息转成流信息给到了标准输出
        // 同样 就可以从 System.in 中获取用户输入的内容，使用 Scanner时，通常这样初始化 new Scanner(System.in);

        // getenv 获取当前系统的环境变量
        System.out.println("=====getenv=====");
        Map<String, String> map = System.getenv();
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                System.out.println(s + " = " + s2);
            }
        });

        System.out.println(System.getenv("JAVA_HOME"));  // 获取指定的环境变量，没有返回 null

        // exit 系统退出，接收一个 退出状态码。0：正常退出 ， 非0：异常退出
        System.exit(18); // Process finished with exit code 18  一般都使用 0 ，这里只是为了演示
        System.out.println("我出不来，可恶");  // 程序提前退出，这里不打印

    }
}
