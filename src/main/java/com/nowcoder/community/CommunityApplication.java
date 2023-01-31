package com.nowcoder.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@MapperScan({"com.nowcoder.community.dao"})
public class CommunityApplication {

    @PostConstruct//管理bean的生命周期,主要管理初始化方法
    public void init() {
        // 解决netty启动冲突的问题
        //若启动了redis再启动es则会抛异常 显示服务已启动
        // NettyRuntime.setAvailableProcessors()//redis
        // Netty4Utils.setAvailableProcessors()//es
        System.setProperty("es.set.netty.runtime.available.processors", "false");//设置系统属性,底层自动转化
    }

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

}
