package com.zhangjian.异常.异常处理;


/**
 * try-catch-finally 异常处理说明
 *   try块用于包含可能出现错误的代码；catch块用于处理try块中发生的异常；
 *   finally块用于强制执行的逻辑，即不管前面有没有异常都会执行finally中的代码，优先级甚至高于 try/catch 中的return语句
 * <p>
 * 语法：
     * try{
     * // 可能发生异常的代码
     * }catch(异常类型){
     * // 对发生的异常进行处理
     * }finally{
     * // 强制逻辑
     * }
 * <p>
 * 注意事项：
     * 1、如果异常发生了，则try块中异常发生位置后面的代码不再执行，直接进入 catch/finally 块中
     * 2、如果没有发生异常，则顺序执行try块代码，不会进入 catch块，但会进入 finally块(如果有)
     * 3、可以有多个catch语句块，用于捕获不同的异常。要求父类异常在后，子类异常在前。如果有异常发生，只会匹配一个catch语句
     * 4、可以直接使用 try-catch ，不要 finally 块
     * 5、也可以直接使用 try-finally ，这种用法相当于没有捕获异常，因此程序在执行完 finally 后会直接退出
 */
public class TryCatchFinally {
    public static void main(String[] args) {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
//        demo5();
        System.out.println(important());
    }

    public static void demo1() {
        System.out.println("demo1============");
        try {
            String name = null;
            System.out.println(name.getClass());
            System.out.println("理论上我出不来");
        } catch (Exception e) {
            System.out.println("异常处理，异常信息： " + e.getClass());
        } finally {
            System.out.println("不管怎样都要执行到我");
        }
    }

    public static void demo2() {
        System.out.println("demo2============");
        try {
            String name = "王也";
            System.out.println(name.getClass());
            System.out.println("正常执行到我");
        } catch (Exception e) {
            System.out.println("异常处理，异常信息： " + e.getClass());
        } finally {
            System.out.println("不管怎样都要执行到我");
        }
    }

    public static void demo3() {
        System.out.println("demo3============");
        try {
            // 这部分空指针
            String name = "王也";
            name = null;
            System.out.println(name.getClass());

            // 上面空指针引发后，这部分的异常将不会被触发
            int num1 = 10;
            int num2 = 0;
            System.out.println(num1 / num2);

            System.out.println("理论上我出不来");
        } catch (NullPointerException e) {
            System.out.println("空指针 " + e.getClass());
        } catch (ArithmeticException e) {
            System.out.println("算术异常 " + e.getClass());
        } catch (Exception e) {
            System.out.println("异常处理，异常信息： " + e.getClass());
        } finally {
            System.out.println("不管怎样都要执行到我");
        }
    }

    public static void demo4() {
        // finally 代码块可以没有
        System.out.println("demo4============");
        try {
            String name = null;
            System.out.println(name.getClass());
            System.out.println("理论上我出不来");
        } catch (Exception e) {
            System.out.println("异常处理，异常信息： " + e.getClass());
        }
    }

    public static void demo5() {
        // catch 代码块也可以没有
        System.out.println("demo5============");
        try {
            String name = null;
            System.out.println(name.getClass());
            System.out.println("理论上我出不来");
        } finally {
            System.out.println("我执行完就直接异常退出");
        }
    }

    public static int important() {
        int i = 1;
        try {
            String name = "王也";
            System.out.println(name.getClass());

            // 上面空指针引发后，这部分的异常将不会被触发
            int num1 = 10;
            int num2 = 0;
            System.out.println(num1 / num2);

            System.out.println("理论上我出不来");
            return i;
        } catch (NullPointerException e) {
            System.out.println("空指针 " + e.getClass());
            return i;
        } catch (ArithmeticException e) {
            System.out.println("算术异常 " + e.getClass());

            // 因为有 finally 代码块存在，return之前 会优先执行 finally 代码
            // 如果 finally 中也有 return 语句，那么 finally return 执行后，方法结束
            // 如果 finally 中没有 return 语句，那么代码将再次 回到当前 catch ，执行当前的return
            // 注意：finally 中对 基础类型变量的修改，不会影响到 catch 的返回值。
            //      catch 在进入 finally时，会现将当前 i 存到临时变量，然后将 i 给 finally，执行完 finally 后，catch 发挥的则是 临时变量
            return ++i; // return 2
        } catch (Exception e) {
            System.out.println("异常处理，异常信息： " + e.getClass());
            return i;
        } finally {
            i++;
            System.out.println("不管怎样都要执行到我，finally 中 i=" + i); // i = 3
        }
    }
}
