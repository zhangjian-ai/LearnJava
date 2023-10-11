package com.zhangjian.面向对象高级.接口;

/**
 * 接口的多态：
 *   1、一个类实现了某个接口，与这个类继承了某个父类，在多态特性方面高度相似。这一点在 InterfaceIntroduction 中已经说明并有代码演示
 *   2、如果一个接口A继承了其它接口C，而类B又实现了接口A，那么 类B 与 接口A 之间的多态特性，也将在 类B 与 接口C 之间体现。这与类的多级继承也高度相似
 */
public class ImplementsPolymorphism {
    public static void main(String[] args) {
        testPolymorphismArray();
        testPolymorphismPass();
    }

    // 测试下接口在数组中的多态
    public static void testPolymorphismArray(){
        TypeC[] cons = new TypeC[2];
        cons[0] = new Phone();
        cons[1] = new Camera();

        for (TypeC con : cons) {
            con.connect();

            if (con instanceof Phone){ // instanceof 判断的是对象的 运行类型
                ((Phone) con).call(); // 向下转型
            }

            if (con instanceof Camera){
                ((Camera) con).photo();
            }
        }
    }

    // 测试多态传递
    public static void testPolymorphismPass(){
        // Phone 实现了 TypeC 接口，因此可以向上转型
        // 编译类型是 TypeC 运行类型是 Phone
        TypeC phone = new Phone();

        // 由于TypeC继承了Usb，因此 Usb 和 Phone 之间同样有多态特性
        Usb phone1 = new Phone();

        System.out.println("多态传递OK");
    }
}

interface Usb{
    void connect();
}

interface TypeC extends Usb{}

class Phone implements TypeC{
    @Override
    public void connect() {
        System.out.println("手机连接成功");
    }

    public void call(){
        System.out.println("手机可以打电话");
    }
}

class Camera implements TypeC{
    @Override
    public void connect() {
        System.out.println("相机连接成功");
    }

    public void photo(){
        System.out.println("相机可以拍照");
    }
}
