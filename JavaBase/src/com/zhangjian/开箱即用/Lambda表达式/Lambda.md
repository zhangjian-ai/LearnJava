## Lambda

Lambda函数，也称为Lambda表达式，是Java 8版本新增的一项功能。它提供了一种简洁的方式来定义小型匿名函数，这些函数可以作为参数传递给其他方法或作为返回值使用。

Lambda主要有两种用法：

1. 实现一个函数式接口，可以省略类名和方法名。语法如下：

    ```java
    // 1. 无参
    () -> {...}
    
    // 2. 有参
    (参数列表) -> {...}
    ```

2. 方法引用。简单地说，我们可以使用方法引用从Lambda函数中调用方法，方法接收参数并自动执行

    ```java
    Object::method
    ```



### 实现函数式接口

> 只有一个抽象方法的接口就是函数式接口，比如常用的 Runnable 、Callable、Consumer、Comparator、Predicate 等接口，都是函数式接口

**示例一：自定义函数式接口**

```java
package com.zhangjian.开箱即用.Lambda表达式;

import javax.sql.rowset.Predicate;
import java.util.Comparator;
import java.util.function.Consumer;

public class Lambda {
    public static void main(String[] args) {
        // 1. 使用无参匿名函数。
        //    这里的匿名函数其实就是 show 的具体实现，并同时代表了接口的实现子类。因为函数式接口中，就一个抽象方法，所以可直接这样写
        showJob(() -> {
            System.out.println("天下攘攘皆为利往~");
        });

        // 2. 使用带参的匿名函数。
        //    同样的道理，接口中只有一个抽象方法，那么表达式就是 getScore 的具体实现，并同时代表了接口的实现子类。
        //    有其他非抽象方法，不影响使用Lambda表达式
        statJob((scores) ->{
            double sum = 0;
            for (Double score : scores) {
                sum += score;
            }
            return sum;
        }, 99.8, 89.0, 95.7, 60.1, 77.6);
    }

    public static void showJob(showInfo info){
        info.show();
    }

    public static void statJob(statScore score, Double... scores){
        // 调用静态方法
        score.desc();

        // 调用抽象方法，由子类实现
        System.out.println(score.getScore(scores));
    }
}

interface showInfo {
    void show();
}

interface statScore {
    double getScore(Double... scores);

    default void desc() {
        System.out.println("这是一个分数统计的接口");
    }
}
```



### 方法引用

```java
package com.zhangjian.开箱即用.Lambda表达式;

import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Consumer;

public class Lambda {
    public static void main(String[] args) {
        // 1. 成员方法引用
        ArrayList<String> list = new ArrayList<>();
        list.add("Jack");
        list.add("Smith");
        list.add("Linda");

        // 1.1 forEach 接收一个Consumer实现实例，可以直接用 Lambda 表达式实现。
        list.forEach((str) -> {
            System.out.println("姓名：" + str);
        });

        // 1.2 上面 Lambda 表达式是程序员手动实现了抽象方法的处理方式；
        //     而 方法引用则可以直接时候现有的方法，代替手动实现，在一些场景会更加简洁。要注意的是：引用方法参数列表和使用方给到的参数 个数 和 类型都要 匹配
        list.forEach(System.out::println);

        // 2 静态方法同样适用
        list.forEach(Demo::showInfo);

        // 3 通过引用new来使用构造器。
        list.forEach(Demo::new);

        for (Demo name : Demo.names) {
            System.out.println(name);
        }

        // 4 多个参数演示
        HashMap<String, Integer> map = new HashMap<>();
        map.put("语文", 93);
        map.put("数学", 98);
        map.put("英语", 88);

        // 4.1 同样可以使用 Lambda 直接处理
        map.forEach((key, val) -> {
            System.out.println("科目：" + key + " 成绩：" + val);
        });

        // 4.2 方法引用。参数列表数据类型、个数一定要匹配
        map.forEach(Demo::showScore);
    }
}

class Demo{
    // 使用一个集合保存实例
    public static ArrayList<Demo> names = new ArrayList<>();

    private String name;

    public Demo(String name) {
        this.name = name;
        names.add(this); // 把自己放到列表中
    }

    public static void showInfo(String info){
        System.out.println("name: " + info);
    }

    public static void showScore(String key, Integer val){
        System.out.println("[通过引用现成方法] 科目：" + key + " 成绩：" + val);
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                '}';
    }
}
```



### 综合案例

1. 实现 Runnable 和 Callable

    ```java
    package com.zhangjian.开箱即用.Lambda表达式;
    
    import javax.sql.rowset.Predicate;
    import java.util.ArrayList;
    import java.util.Comparator;
    import java.util.HashMap;
    import java.util.concurrent.*;
    import java.util.function.Consumer;
    
    public class Lambda {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            Runnable task1 = () -> System.out.println("hello world!");
            Callable task2 = () -> {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum += i;
                }
                return sum;
            };
    
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2));
            poolExecutor.execute(task1);
            Future future = poolExecutor.submit(task2);
            System.out.println(future.get());
        }
    }
    ```

2. 实现Consumer

    ```java
    package com.zhangjian.开箱即用.Lambda表达式;
    
    import javax.sql.rowset.Predicate;
    import java.util.ArrayList;
    import java.util.Comparator;
    import java.util.HashMap;
    import java.util.concurrent.*;
    import java.util.function.Consumer;
    
    public class Lambda {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            ArrayList<String> strings = new ArrayList<>();
            Consumer<String> consumer = strings::add; // 方法引用。直接使用 ArrayList的add作为 Consumer accept 的具体实现
    
            consumer.accept("歌尔D罗杰");
            consumer.accept("蒙奇D路飞");
            consumer.accept("文斯莫克山治");
    
            strings.forEach(System.out::println); // 再次使用 System.out::println 作为 Consumer accept 的具体实现
        }
    }
    ```

3. 实现 Comparator

    ```java
    package com.zhangjian.开箱即用.Lambda表达式;
    
    import javax.sql.rowset.Predicate;
    import java.util.ArrayList;
    import java.util.Comparator;
    import java.util.HashMap;
    import java.util.concurrent.*;
    import java.util.function.Consumer;
    
    public class Lambda {
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            Comparator<Score> comparator = (score1, score2) -> {
                double diff = score1.getScore() - score2.getScore();
                if (diff > 0) return 1;
                if (diff < 0) return -1;
                return 0;
            };
    
            ArrayList<Score> scores = new ArrayList<>();
            scores.add(new Score(99.8));
            scores.add(new Score(101.1));
            scores.add(new Score(87.4));
            scores.add(new Score(93.5));
    
            scores.sort(comparator);
    
            scores.forEach(score -> {
                System.out.print(score.getScore() + " ");
            });
        }
    }
    
    class Score{
        private double score;
    
        public Score(double score) {
            this.score = score;
        }
    
        public double getScore(){
            return this.score;
        }
    }
    ```

    