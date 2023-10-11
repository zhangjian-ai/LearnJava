package com.zhangjian.面向对象高级.注解;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * readme 文档代码演示
 */
public class SelfDefineAnnotation {
    public static void main(String[] args) {
//        AnnotationUtils.showFruitInfo(Apple.class);

        // repeatable
        AnnotationUtils.showFruitInfo(PlayGame.class);
    }
}

// 定义三个注解
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FruitName {
    String value() default "";
}

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FruitColor {
    public enum Color {GREEN, RED, BLUE;}

    Color value() default Color.RED;
}

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FruitProvider {
    int id() default -1;

    String name() default "";

    String address() default "";
}

// 使用注解
class Apple {
    @FruitName("Apple")
    public String name;

    @FruitColor(FruitColor.Color.RED)
    private String color;

    @FruitProvider(id = 1001, name = "陕西红富士", address = "陕西渭南红富士苹果基地9999号")
    private String address;
}


// 玩家注解
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface People {
    Game[] value() ;
}

// 游戏注解
// @Repeatable(People.class) 说明：
// 1、Repeatable 表示 Game 注解时刻重复的，即这个注解可多次作用在同一个目标上面
// 2、People.class 是 Repeatable 的入参，表示 申明 可重复注解Game 的 包含注解为 People
@Repeatable(People.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Game {
    String value() default "";
}

// 使用可重复注解
// 代码层面看起来是Game，但实际 PlayGame 的注解是 Game的包含注解 People
// Game 的多次标注，都会被收集到 People 的 Game[] 中
@Game(value = "LOL")
@Game(value = "PUBG")
@Game(value = "NFS")
@Game(value = "Dirt4")
class PlayGame {
}


// 注解工具
class AnnotationUtils {
    public static void showFruitInfo(Class<?> cls) {

        if (cls == Apple.class){
            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {
                System.out.println(field);
                if (field.isAnnotationPresent(FruitName.class)) {
                    FruitName fruitName = field.getAnnotation(FruitName.class);
                    System.out.println("水果的名称： " + fruitName.value());
                }

                if (field.isAnnotationPresent(FruitColor.class)) {
                    FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                    System.out.println("水果的颜色： " + fruitColor.value());
                }

                if (field.isAnnotationPresent(FruitProvider.class)){
                    FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                    System.out.println("水果供应商信息如下：");
                    System.out.println("  厂家名称： " + fruitProvider.name());
                    System.out.println("  厂家地址： " + fruitProvider.address());
                }

            }
        }

        if (cls == PlayGame.class){
            Annotation[] annotations = cls.getAnnotations();

            for (Annotation annotation : annotations) {
                if (annotation instanceof People){
                    for (Game game : ((People) annotation).value()) {
                        System.out.println(game.value());
                    }
                }

                if (annotation instanceof Game){
                    System.out.println("不该有啊");
                }
            }

        }

    }
}
