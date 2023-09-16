package com.zhangjian.前期准备.逻辑控制;
import java.util.Scanner;

/**
 * switch case 细节：
 * 1、表达式数据类型应和case子句后的常量数据类型一致，或者是可以自动转换的数据类型；
 * 2、switch(表达式) 中 表达式 的返回值类型必须是 byte/short/int/char/enum/String 类型之一；
 * 3、case 子句的值必须是常量，不能是变量；
 * 4、default 子句是可选的，当所有case子句都不匹配时，执行default；
 * 5、break语句用来在执行完一个case分之后，跳出switch代码块，如果没有break，则程序会跳过下一个case子句的匹配条件，直接执行
 *    其匹配成功后的代码，直到程序结束或遇到break。
 */
public class SwitchExercise {
    public static void main(String[] args) {
        char a = 'a';

        switch (a) {
            case 96: // char 和 int 是可以互相转换的类型，故编译ok
                System.out.println("a 不是这个值哦");
                break;
            case 'b':
                System.out.println("也不是b");
                break;
            case 'a': // case 子句后无break 将直接穿透到下一个case子句内去执行
                System.out.println("来了老弟");
            case '2':
                System.out.println("我被穿透了啊。。。");
                break;
            default:
                System.out.println("default 语句写不写break都无所谓了，因为程序都会结束");
        }

        // 练习：根据月份输出季节
        // case 子句内可以不写任何东西，直接穿透到下一个case子句内部
        // 这里更好的方式是 月份 对 3 取余来判断季节
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入月份");

        int month = scanner.nextInt();

        switch (month){
            case 12:
            case 1:
            case 2:
                System.out.println("winter");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("spring");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("summer");
                break;
            default:
                System.out.println("autumn");
        }


    }
}
