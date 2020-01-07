package com.wb.springboot.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 主程序
 *
 * @EnableCaching: 开启缓存
 */
@MapperScan("com.wb.springboot.cache.mapper")
@SpringBootApplication
@EnableCaching
public class SpringApplication_Cache {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplication_Cache.class, args);
    }

}
