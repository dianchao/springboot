package com.dianchao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.dianchao.mapper")
@EnableCaching
public class SpringBootCacheApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApp.class, args);
    }
}
