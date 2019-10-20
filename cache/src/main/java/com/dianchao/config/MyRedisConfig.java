package com.dianchao.config;

import com.dianchao.bean.Department;
import com.dianchao.bean.Employee;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class MyRedisConfig {
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Employee> employeeRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Employee>(Employee.class));
        return template;
    }

    //当有多个相同类型的Bean，需要指定默认的组件
    //将employeeCacheManager作为默认缓存管理器
    @Primary
    @Bean
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object, Employee> employeeRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(employeeRedisTemplate);
        //发现key多了一个前缀
        //使用前缀，默认会将cacheName作为key的前缀
        cacheManager.setUsePrefix(true);

        return cacheManager;
    }


    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Department> deptRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Department>(Department.class));
        return template;
    }

    @Bean
    public RedisCacheManager departmentCacheManager(RedisTemplate<Object, Department> deptRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(deptRedisTemplate);
        //发现key多了一个前缀
        //使用前缀，默认会将cacheName作为key的前缀
        cacheManager.setUsePrefix(true);

        return cacheManager;
    }
}
