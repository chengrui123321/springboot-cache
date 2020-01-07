package com.wb.springboot.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * 基于缓存的配置类
 */
@Configuration
public class MyCacheConfig {

    //指定自己的key生成策略
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {

            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName() + "_" + Arrays.toString(params);
            }
        };
    }

}
