package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.service.UserService;
import com.cloud_note.util.NoteResult;

@Controller("userChangePassword")
public class UserChangePassword {
         @Resource(name="userService")
         private UserService service;
         
         
         @RequestMapping("/user/change_password.do")
         @ResponseBody
        public NoteResult<Object> excute(String userId,String password,String newpassword){
        	System.out.println("获取的修改信息："+
                          "userId"+userId+" "+
                          "password:"+password+
                          "newpassword"+newpassword);
        	NoteResult<Object> result= service.changePassword(userId, password, newpassword);
        	System.out.println("修改结果"+result);
           return result;
        }
}
