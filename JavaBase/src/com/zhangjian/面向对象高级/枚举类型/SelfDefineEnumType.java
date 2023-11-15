package com.zhangjian.面向对象高级.枚举类型;


/**
 * 自定义枚举类
 *    1、不需要提供属性的set方法，因为枚举类通常为只读
 *    2、对 枚举对象 使用 final + static 共同修饰，方便通过类名直接访问，且禁止修改
 *    3、枚举对象命名规则同常量一致，全部大写
 *    4、枚举类型中，可以根据需要设置多个属性，或不要任何属性
 *
 * 实现步骤：
 *    1、构造器私有化。避免外部创建对象
 *    2、本类中创建约定好的 枚举对象
 *    3、对外暴露这些 枚举对象(final static)
 *    4、可以提供 get 方法，不能提供 set
 */
public class SelfDefineEnumType {
    public static void main(String[] args) {
        // 使用枚举类
        System.out.println(SelfDefineSeasonEnum.SPRING);
        System.out.println(SelfDefineSeasonEnum.WINTER.getName());
        System.out.println(SelfDefineSeasonEnum.SUMMER.getDesc());
        System.out.println(SelfDefineSeasonEnum.AUTUMN);
    }
}

class SelfDefineSeasonEnum{
    private String name;
    private String desc;

    // 构造器私有化
    private SelfDefineSeasonEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "SelfDefineSeasonEnum{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    // 创建枚举实例
    public final static SelfDefineSeasonEnum SPRING = new SelfDefineSeasonEnum("春", "温暖");
    public final static SelfDefineSeasonEnum SUMMER = new SelfDefineSeasonEnum("夏", "炎热");
    public final static SelfDefineSeasonEnum AUTUMN = new SelfDefineSeasonEnum("秋", "凉爽");
    public final static SelfDefineSeasonEnum WINTER = new SelfDefineSeasonEnum("冬", "寒冷");

}
