package com.nowcoder.community.controller.advice;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-28 23:41
 **/

//在不配置参数时候 该注解作用是声明管理所有的controller
//当配置参数时 该注解会扫描带有特定注解的bean
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {
    private static final Logger logger= LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)//处理异常
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //记录日志异常   概况
        logger.error("服务器发生了异常: "+e.getMessage());
        //记录一条有序的详细异常
        for(StackTraceElement element: e.getStackTrace()){
            logger.error(element.toString());
        }
        String xRequestedWith=request.getHeader("x-requested-with");
        //判断是否为异步请求
        if("XMLHttpRequest".equals(xRequestedWith)){
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer=response.getWriter();
            writer.write(CommunityUtil.getJSONString(1,"服务器异常!"));
        }else {
            //普通请求
            response.sendRedirect(request.getContextPath()+"/error");
        }
    }
}