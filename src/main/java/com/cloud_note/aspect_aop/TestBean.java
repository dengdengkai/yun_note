package com.cloud_note.aspect_aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestBean {
   @After("execution(* com.cloud_note.dao.UserDAO.*(..))")
	public void excute(){
	   System.out.println("我在执行后执行");
   }
}
