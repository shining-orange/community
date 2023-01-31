package com.nowcoder.community.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-29 09:18
 **/
@Component //使用容器管理
@Aspect//说明该类不是一个普通的组件 而是一个方便组件
public class ServiceLogAspect {
    private static final Logger logger= LoggerFactory.getLogger(ServiceLogAspect.class);

    //处理所有的业务组件和所有的方法
    @Pointcut("execution(* com.nowcoder.community.service.*.*(..))")
    public void pointcut(){

    }
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        //织入到前部作用于记录ip:考虑到用户不一定登录
        //用户[1.2.3.4](用户ip),在[xxx](时间),访问了[com.nowcoder.community.service.xxx](访问了xxx方法)
        //强转成子类型,得到更多方法
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes==null) {
            return;
        }
        HttpServletRequest request=attributes.getRequest();

        //获取ip
        String ip=request.getRemoteHost();
        //设置获取的时间格式
        String now=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //得到目标的方法名称和类型名称
        String target=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        logger.info(String.format("用户[%s],在[%s],访问了[%s].",ip,now,target));
    }
}