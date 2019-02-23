package com.cloud_note.aspect_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditBean {
   
	@Around("within(com.cloud_note.service..*)")
	public Object audit(
			ProceedingJoinPoint  point)
	                         throws Throwable{
    	  Object obj=null;
    	try{
    		long timeStart=System.currentTimeMillis();
    	    obj= point.proceed(); //将其看做执行service过程
    	    long timeEnd=System.currentTimeMillis();
    	    String str=point.getSignature().toString();//讲调用的service名字调出来
    	    System.out.println(str+"耗时："+(timeEnd-timeStart));
    	  }
    	  catch(Throwable e){
    		  e.printStackTrace();
    		  throw e;
    	  }
		return obj;
     }
}
