package com.cloud_note.aspect_aop;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
 
@Component  //被spring容器扫描到
@Aspect    //将该类作为切面组件
public class ExceptionBean {
    //e是目标组件抛出来的对象
	@AfterThrowing(throwing="e",
			pointcut="within(com.cloud_note.service..*)")
	public void excute(Exception e){
		try{
		//将异常信息输入文件
		FileWriter fw = 
				new FileWriter("D:\\note_error.log",true); 
		//指定保存路径,true代表以追加的方式
		PrintWriter pw = new PrintWriter(fw);
		//利用pw对象写入异常信息
		Date time = new Date();
		SimpleDateFormat sdf = 
				new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
		String timeStr =sdf.format(time);
		pw.println("****************************");
		pw.println("*异常类型："+ e);
		pw.println("*异常时间："+ timeStr);
		pw.println("*****异常详细信息*********");
		e.printStackTrace(pw);
		pw.close();
		fw.close();
		}
		catch(Exception ex){
		
			System.out.println("记录异常失败");
		}
	}
}
