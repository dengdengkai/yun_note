package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.User;
import com.cloud_note.service.UserService;
import com.cloud_note.util.NoteResult;

@Controller("userAddController") //�����������������
public class UserAddController {
     
	@Resource(name="userService")  //����ע��
	private UserService userService;
	
	@RequestMapping("/user/add.do")  //����������
	@ResponseBody //�����صĶ�����json�ַ���
	public NoteResult<User> excute(
			     String name,String password,String nick){
		NoteResult<User> result =
		                 userService.addUser(name, password, nick);		
		System.out.println("���յ��� ������"+name+"���룺 "+password+"�ǳ�: "+nick);
		System.out.println("result:"+result);
		return result;
	}
	
}
