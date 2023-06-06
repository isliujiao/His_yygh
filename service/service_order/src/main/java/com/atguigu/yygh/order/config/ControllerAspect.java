package com.atguigu.yygh.order.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 切面测试
 */
@Component
@Aspect
public class ControllerAspect {

    @Pointcut("execution(* com.atguigu.yygh.order.api..*.*(..))")
    public void pointCut() {
        //该方法仅用于扫描controller包下类中的方法，而不做任何特殊的处理。
    }

//    @After("pointCut()")
//    public void doAfter(JoinPoint joinPoint) {
//        System.out.println("==== doAfter 方法进入了====");
//        Signature signature = joinPoint.getSignature();
//        String method = signature.getName();
//        System.out.println("方法{}已经执行完" + method);
//    }

}
