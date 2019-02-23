
package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.User;
import com.cloud_note.service.UserService;
import com.cloud_note.util.NoteResult;



@Controller("userLoginController")
//ע������ע�ⲻд�����Ĭ��Ϊ�ĵ�һ����ĸСд��
//��������д��һ��
public class UserLoginController {
    
	@Resource(name="userService")
	private UserService userService;
	
   @RequestMapping("/user/login.do")  //����handelrMappingע��
   @ResponseBody   //JSON���
   public NoteResult<User> execute(
		   String name,String password){
		
	   System.out.println("controller()ִ���ˣ� " +
	   		"��̨���յ����� ������"+name+" password:"+password);
		
		//����UserService�����¼����
		NoteResult result = 
				userService.checkLogin(name, password);
		return result;
	
	}
}


/*
 * @ResponseBody��jackson��ע�⣬�����·��������صĶ���
 * ת����һ��json���ݸ�ʽ
 * NoteResult �����������
 *  status(int) ,msg(string) data(T), �˴���data(User)
 *  ���صĸ�ʽ����
 *  {"status":����,"msg":"״̬˵��";"data":{"cn_user_name":"","cn_user_password":"",...}}
 *  ������0��1��2�е�һ��
 */
/********************��ϸ�ο�springmvc02**********************************
 * ����ֵ�����ַ�ʽ���˴�����Ĭ�ϵķ�ʽ�����β����������
 * ��Ӧ��name������һ�¡�
 * **************************************
 * ��һ�֣��β�ΪHttpServletRequest request
 * ��ȡ������String s=request.getParameter("����������name����");
 * 
 * ***************************************
 * �ڶ��֣����β�����ǰ�����ע�ⷽʽ @RequestParam
 * ע�������е��ַ����Ǳ���������name����
 *�磺 public String login2(@RequestParam("adminCode") String adminCode,
 *          @RequestParam("pwd") String pwd)
 * *******************************************
 * �����֣���װ��һ��javabean
 * �����е�name�����֣�д��һ��javabean, ��javabean���Զ�Ӧ��
 * һģһ��������     
 * �磺public String login3(AdminParam ap) 
 * ��AdminParam���а���һģһ���Ķ�Ӧ����   
 * ************************************************ 
 */

/**
 * 
 */