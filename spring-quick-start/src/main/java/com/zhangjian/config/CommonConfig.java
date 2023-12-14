package com.zhangjian.config;

import com.zhangjian.controller.PoemController;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 配置类
public class CommonConfig {

    @Bean // 将第三方对象交给 IOC 容器管理
          // 通过@Bean的 name/value 属性可以指定bean的名称，如果没有指定 则默认就是方法名
    public SAXReader saxReader(PoemController poemController){ // 当引入第三方bean需要使用已有的bean对象时，直接在形参当中声明即可
        System.out.println(poemController);
        return new SAXReader();
    }
}
