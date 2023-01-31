package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-29 16:01
 **/
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> template =new RedisTemplate<>();
        template.setConnectionFactory(factory);//配置工厂

        //指定数据转化的方式也就是key的序列化方式
        //   配置一个可以序列化字符串的序列化器
        template.setKeySerializer(RedisSerializer.string());
        //设置普通values的序列方式
        //   配置一个json的序列化器
        template.setValueSerializer(RedisSerializer.json());
        //设置hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        //设置hash的values的序列化方式
        template.setHashValueSerializer(RedisSerializer.json());

        //使触发参数化生效
        template.afterPropertiesSet();
        return template;
    }
}