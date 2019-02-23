package com.cloud_note.aspect_aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component  //扔给容器管理
@Aspect     //表示是切面代码
public class LoggerBean {
   @Before("within(com.cloud_note.controller..*)")
	public void logController(){
    	System.out.println("AOP功能注入！");
    	
    }
}
