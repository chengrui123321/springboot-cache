package com.wb.springboot.cache;

import com.wb.springboot.cache.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {
    @Qualifier("empRedisTemplate")
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testObject() {
        redisTemplate.boundValueOps("emp").set(new Employee().setId(1).setLastName("李四").setGender(1));
    }

    @Test
    public void testAdd() {
        stringRedisTemplate.boundValueOps("name").set("张撒比");
    }

}
