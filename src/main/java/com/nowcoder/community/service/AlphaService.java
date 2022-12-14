package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-13 01:04
 **/
@Service
@Scope("prototype")//多例
//@Scope//单例
public class AlphaService {

    @Autowired//自动装配 //依赖注入
    public AlphaDao alphaDao;

    public AlphaService() {
        System.out.println("实例化AlphaService");
    }

    @PostConstruct //在构造器之后执行
    public void init(){
        System.out.println("初始化AlphaService");
    }
    @PreDestroy //在销毁之前执行
    public void destroy(){
        System.out.println("销毁AlphaService");
    }
    public  String find(){
        return alphaDao.select();
    }
}