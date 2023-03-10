package com.nowcoder.community.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

/**
* @program: community
* @description:
* @author: DM
* @create: 2023-01-30 14:54
**/
@Configuration
public class WkConfig {
    private static final Logger logger= LoggerFactory.getLogger(WkConfig.class);
    @Value("${wk.image.storage}")
    private String wkImgeStorage;
    @PostConstruct//初始化
    public void init(){
        //创建图片目录
        File file =new File(wkImgeStorage);
        if (!file.exists()){
            file.mkdir();
            logger.info("创建WK图片目录: "+wkImgeStorage);
        }
    }
}