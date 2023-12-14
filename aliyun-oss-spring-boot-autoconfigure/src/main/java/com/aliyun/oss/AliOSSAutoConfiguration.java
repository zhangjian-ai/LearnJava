package com.aliyun.oss;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 配置类
@EnableConfigurationProperties({AliOSSProperties.class}) // 将 AliOSSProperties 交给 IOC 容器管理
public class AliOSSAutoConfiguration {

    @Bean
    public AliOSSUtils aliOSSUtils(AliOSSProperties aliOSSProperties){ // 对于已经在 IOC 中的bean，直接申明参数使用即可
        AliOSSUtils aliOSSUtils = new AliOSSUtils();
        aliOSSUtils.setAliOSSProperties(aliOSSProperties);
        return aliOSSUtils;
    }
}
