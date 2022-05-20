package com.example.finalexamlibraryvar1kalzhigitovnurbol.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ConfLogger {

    Logger log = LoggerFactory.getLogger(this.getClass());


    @Pointcut("@annotation(com.example.finalexamlibraryvar1kalzhigitovnurbol.config.PointExecution)")
    public void PointExecutionMethods(){
    }

    @Pointcut("execution(* com.example.finalexamlibraryvar1kalzhigitovnurbol.service..*(..))")
    public void allServiceMethods(){
    }

    @Pointcut("execution(* com.example.finalexamlibraryvar1kalzhigitovnurbol.jms.MessageReciever.receiveMessage(*))")
    public void messageSender(){
    }

    @Pointcut("execution(* com.example.finalexamlibraryvar1kalzhigitovnurbol.jms.MessageReciever.receiveMessageAdmin(*))")
    public void messageSenderAdmin(){
    }


    @Before("PointExecutionMethods()")
    public void AdviceBefore(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("Programmer Ready to Work: " + methodName);
    }

    @After("allServiceMethods()")
    public void AdviceAfter(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("Method was initialized" + methodName);
    }

    @After("messageSender()")
    public void AdviceSenderAfter(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("New Message to Admin!!!!!!!!!!!!!!!!!!!");
    }

    @After("messageSenderAdmin()")
    public void AdviceSenderAdminAfter(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("Admin send message to user");
    }

}
