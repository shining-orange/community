package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-13 01:12
 **/
@Configuration
public class AlphaConfig {
    @Bean //方法名就是bean的名字 此方法返回的对象将会被装配到容器中
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}