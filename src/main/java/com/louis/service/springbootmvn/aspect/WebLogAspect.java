package com.louis.service.springbootmvn.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * WebLogAspect
 *
 * @author wangxing
 * @date 2019-5-22
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    /**
     * 以自定义 @WebLog 注解为切点
     */
    @Pointcut("@annotation(com.louis.service.springbootmvn.aspect.WebLog)")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {


        // 获取 @WebLog 注解的描述信息
        String methodDescription = "dddddd";
        log.info("========================================== Start ==========================================");
        log.info(methodDescription);

    }

    @After("webLog()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        log.info("=========================================== End ===========================================");
    }

    @AfterReturning("webLog()")
    public void doAfterReturning() throws Throwable{
        log.info("=========================================== returning ===========================================");
    }

}
