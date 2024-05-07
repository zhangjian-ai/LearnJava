package com.zhangjian.泛型;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericType {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
    }

    // 使用Junit 进行测试，静态方法不能使用
    @Test
    public void testDAO(){
        DAO<User> dao = new DAO<>();

        dao.save("001", new User(1, 18, "Jack"));
        dao.save("002", new User(2, 28, "Tom"));
        dao.save("003", new User(3, 38, "Smith"));
        dao.save("004", new User(4, 48, "Queen"));

        System.out.println(dao.get("002"));

        dao.update("003", new User(3, 43, "Robot"));
        dao.delete("004");

        List<User> list = dao.list();
        System.out.println(list);
    }
}


class DAO<T>{
    private Map<String, T> map = new HashMap<>();

    public void save(String id, T entity){
        map.put(id, entity);
    }

    public T get(String id){
        return map.get(id);
    }

    public void update(String id, T entity){
        map.put(id, entity);
    }

    public void delete(String id){
        map.remove(id);
    }

    public List<T> list(){
        // 取出map中的T放入 list中
        List<T> ts = new ArrayList<>(map.values());

        return ts;
    }
}

class User{
    // 想通访问权限和类型，可以这样定义多个变量
    private int id, age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
