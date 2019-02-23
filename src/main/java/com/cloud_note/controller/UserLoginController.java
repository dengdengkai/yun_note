
package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.User;
import com.cloud_note.service.UserService;
import com.cloud_note.util.NoteResult;



@Controller("userLoginController")
//注：上诉注解不写具体的默认为的第一个字母小写，
//与上面填写的一致
public class UserLoginController {
    
	@Resource(name="userService")
	private UserService userService;
	
   @RequestMapping("/user/login.do")  //配置handelrMapping注解
   @ResponseBody   //JSON输出
   public NoteResult<User> execute(
		   String name,String password){
		
	   System.out.println("controller()执行了！ " +
	   		"后台接收到数据 姓名："+name+" password:"+password);
		
		//调用UserService处理登录请求
		NoteResult result = 
				userService.checkLogin(name, password);
		return result;
	
	}
}


/*
 * @ResponseBody是jackson的注解，它将下方方法返回的对象
 * 转换成一个json数据格式
 * NoteResult 类包含的属性
 *  status(int) ,msg(string) data(T), 此处是data(User)
 *  返回的格式会变成
 *  {"status":数字,"msg":"状态说明";"data":{"cn_user_name":"","cn_user_password":"",...}}
 *  数字是0，1，2中的一个
 */
/********************详细参考springmvc02**********************************
 * 请求传值有三种方式，此处采用默认的方式。即形参名字与表单中
 * 对应的name的属性一致。
 * **************************************
 * 第一种：形参为HttpServletRequest request
 * 获取都采用String s=request.getParameter("表单中输入框的name名字");
 * 
 * ***************************************
 * 第二种：在形参类型前面采用注解方式 @RequestParam
 * 注解括号中的字符串是表单中输入框的name名字
 *如： public String login2(@RequestParam("adminCode") String adminCode,
 *          @RequestParam("pwd") String pwd)
 * *******************************************
 * 第三种：封装成一个javabean
 * 将表单中的name的名字，写成一个javabean, 该javabean属性对应的
 * 一模一样的名字     
 * 如：public String login3(AdminParam ap) 
 * 该AdminParam类中包含一模一样的对应属性   
 * ************************************************ 
 */

/**
 * 
 */