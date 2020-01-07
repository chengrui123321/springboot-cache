package com.wb.springboot.cache.config;

import com.wb.springboot.cache.bean.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;

/**
 * 自定义Redis序列化
 */
@Configuration
public class RedisConfig {

    /**
     * 自定义Redis序列化方式
     * @param redisConnectionFactory
     * @return
     * @throws UnknownHostException
     */
    @Bean
    public RedisTemplate<Object, Object> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        RedisSerializer redisSerializer = new Jackson2JsonRedisSerializer(Employee.class);
        template.setDefaultSerializer(redisSerializer);
        return template;
    }

    /**
     * 自定义CacheManager
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisCacheManager empCacheManager(@Qualifier("empRedisTemplate") RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

}
