package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.User;
import com.cloud_note.service.UserService;
import com.cloud_note.util.NoteResult;

@Controller("userAddController") //交给容器管理这个类
public class UserAddController {
     
	@Resource(name="userService")  //依赖注入
	private UserService userService;
	
	@RequestMapping("/user/add.do")  //请求处理配置
	@ResponseBody //将返回的对象变成json字符串
	public NoteResult<User> excute(
			     String name,String password,String nick){
		NoteResult<User> result =
		                 userService.addUser(name, password, nick);		
		System.out.println("接收到的 姓名："+name+"密码： "+password+"昵称: "+nick);
		System.out.println("result:"+result);
		return result;
	}
	
}
