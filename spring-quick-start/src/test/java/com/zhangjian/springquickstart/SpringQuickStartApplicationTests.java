package com.zhangjian.springquickstart;

import com.zhangjian.controller.PoemController;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringQuickStartApplicationTests {

    @Autowired
    private ApplicationContext applicationContext; // IOC容器对象

    @Autowired
    private SAXReader saxReader;

    @Test
    void contextLoads() {
        // 根据 name 获取bean，返回类型默认是 Object
        PoemController bean1 = (PoemController) applicationContext.getBean("poemController");
        System.out.println(bean1);

        // 根据类型获取bean
        PoemController bean2 = applicationContext.getBean(PoemController.class);
        System.out.println(bean2);

        // 根据 name 获取bean，并指定转换类型
        PoemController bean3 = applicationContext.getBean("poemController", PoemController.class);
        System.out.println(bean3);
    }

    @Test
    public void testBean(){
        System.out.println(saxReader);
    }

}
