package com.zhangjian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zhangjian"})
public class SpringQuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringQuickStartApplication.class, args);
    }
}
