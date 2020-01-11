package com.hezhe.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //开启定时任务
@MapperScan(basePackages = "com.hezhe.springboot.mapper")
@SpringBootApplication
public class Springboot1Application {
    public static void main(String[] args) {
        SpringApplication.run(Springboot1Application.class, args);
    }
}
