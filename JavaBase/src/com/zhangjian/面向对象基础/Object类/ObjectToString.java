package com.zhangjian.面向对象基础.Object类;

/**
 * Object 中的 toString:
 *   1、默认返回 全类名+@+哈希值的十六进制
 *       public String toString() {
 *         return getClass().getName() + "@" + Integer.toHexString(hashCode());
 *         }
 *   2、子类往往会重写 toString 来返回对象的属性信息
 *   3、直接输出一个对象时，默认调用 toString 返回打印信息
 *
 */
public class ObjectToString {
    public static void main(String[] args) {
        TS ts = new TS("zuolun", "H", 637213);

        // 下面输出相同内容，都是打印重写后toString方法的返回值
        System.out.println(ts.toString()); // TS{name='zuolun', level='H', salary=637213.0}
        System.out.println(ts); // TS{name='zuolun', level='H', salary=637213.0}

        // Object自有的toString输出
        System.out.println(new Object()); // java.lang.Object@5d624da6
    }
}

class TS{
    private String name;
    private String level;
    private double salary;

    public TS(String name, String level, double salary) {
        this.name = name;
        this.level = level;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "TS{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", salary=" + salary +
                '}';
    }
}
