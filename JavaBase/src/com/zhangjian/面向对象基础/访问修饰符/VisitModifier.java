package com.zhangjian.面向对象基础.访问修饰符;

public class VisitModifier {
    /**
     * 访问修饰符：
     *  java 提供四种访问控制的修饰符号，用于控制 属性（成员变量）和 成员方法 的访问权限（范围）。
     *      public : 公开级别。 本类 同包 子类 其他类 均可以访问；
     *      protected : 受保护级别。 本类 同包 子类 可以访问；
     *      没有修饰符 : 默认级别。 本类 同包 可以访问；
     *      private : 似有级别。 只有本类自身可以访问。
     *
     * 注意：
     * 1、修饰符可以用来类中的 属性 和 成员方法；
     * 2、其中 public 和 默认修饰符 还可以修饰类，访问权限控制和上面一样。那么 使用默认修饰符的类，将不能被其他包中的模块导入；
     */


    public String attr1 = "PublicAttr";
    protected String attr2 = "ProtectedAttr";
    String attr3 = "DefaultAttr";
    private String attr4 = "PrivateAttr";

    public void PublicFunc(){
        System.out.println("PublicFunc");
    }

    protected void ProtectedFunc(){
        System.out.println("ProtectedFunc");
    }

    void DefaultFunc(){
        System.out.println("DefaultFunc");
    }

    private void PrivateFunc(){
        System.out.println("PrivateFunc");
    }


    public void demo(){
        // 本类中可以访问所有访问权限的属性和方法
        System.out.println(attr1 + " " + attr2 + " " + attr3 + " " + attr4);

        PublicFunc();
        ProtectedFunc();
        DefaultFunc();
        PrivateFunc();
    }

}
