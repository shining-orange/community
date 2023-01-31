package com.nowcoder.community.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-29 08:51
 **/

//因为是测试业务逻辑的示例,所以正常开发可将该类注释掉,否则每次启动控制台将打印一堆数据
//@Component //使用容器管理
//@Aspect//说明该类不是一个普通的组件 而是一个方便组件
public class AlphaAspect {

    //其中第一个*代表着返回值(任意返回值都可以) 说明com.nowcoder.community.service包下的所有的类中所有的方法和所有的返回值都要处理
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut(){

    }

    @Before("pointcut()")
    //作用于链接点的开头
    public void before(){

        System.out.println("before");
    }
    @After("pointcut()")
    //作用于链接点的结尾
    public void after(){
        System.out.println("after");
    }
    @AfterReturning("pointcut()")
    //作用于有了返回值以后
    public void afterReturning(){
        System.out.println("afterReturning");
    }
    @AfterThrowing("pointcut()")
    //作用于抛出异常时以后
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }
    @Around("pointcut()")
    //在前面和后面同时织入逻辑以后
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{//连接点
        System.out.println("around before");
        //调用目标对象被处理的逻辑(调用目标组件的返回值:本质是调用原始对象的方法)
        Object obj=joinPoint.proceed();
        System.out.println("around after");
        return obj;
    }

}