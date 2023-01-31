package com.nowcoder.community.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * @author: Tisox
 * @date:
 * @description:
 */

/**
 * 通过注解@Configuration标识这是一个配置类
 * 用于配置生成验证码的kaptcha
 */
@Configuration
public class KaptchaConfig {
    /**
     * 装配
     * @return kaptcha
     */
    @Bean
    public Producer kaptchaProducer(){
        Properties properties = new Properties();
        /*设置宽高*/
        properties.setProperty("kaptcha.image.width","100");
        properties.setProperty("kaptcha.image.height","40");
        /*字体和颜色*/
        properties.setProperty("kaptcha.textproducer.font.size","32");
        properties.setProperty("kaptcha.textproducer.font.color","0,0,0");
        /*生成的字符串范围*/
        properties.setProperty("kaptcha.textproducer.char.string","0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        /*验证码产犊：4位*/
        properties.setProperty("kaptcha.textproducer.char.length","4");
        /*干扰规则*/
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config  = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }

}
