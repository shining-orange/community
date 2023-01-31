package com.nowcoder.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2023-01-18 22:08
 **/
@Configuration
@EnableScheduling//由于定时任务比较敏感,所以如果不配置该项注解则定时任务不会启动
@EnableAsync
public class ThreadPoolConfig {

}