package com.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud_note.dao.UserDAO;
import com.cloud_note.entity.User;
import com.cloud_note.util.NoteResult;
import com.cloud_note.util.NoteUtil;

@Service("userService")
//扫描的spring容器
@Transactional
public class UserServiceImpl implements UserService{

	
	@Resource(name="userDAO")
	private UserDAO userDao;
	
	//登录
	public NoteResult<User> checkLogin(String name, String password) {
		//接收结果数据  
		NoteResult<User> result =
				   new NoteResult<User>();
		//按参数name查询数据库（service层调用dao层）
		User user = userDao.findByName(name);
		//检测用户
		if(user == null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//检测密码
		//先进行md5加密然后与数据库中注册时
		 //被加密的密码对比
		String md5Password
		      = NoteUtil.md5(password);
				 
		if(!user.getCn_user_password()
				.equals(md5Password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
			
		}
		//用户名和密码都正确
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		return result;
	}

	//注册
	public NoteResult<User> addUser(String name, String password, String nick) {
		NoteResult<User> result =
				new NoteResult<User>();
		User user = userDao.findByName(name);
	    
		//用户名占用检测
		if(user != null)
		{
			result.setStatus(1);
			result.setMsg("用户名已存在");
		   return result;	
		}
		//用户名没有被占用
		user = new User();
		user.setCn_user_id(NoteUtil.createdId()); //调用NoteUtil类的静态方法获取id并设置设置id
		user.setCn_user_name(name);
		user.setCn_user_password(NoteUtil.md5(password));//加密密码并设置
		user.setCn_user_nick(nick);
		user.setCn_user_token(null);
		
		userDao.save(user);//调用dao，向数据库插入用户数据
		
		result.setStatus(0);
		result.setMsg("注册成功");
	
		return result;
	}

	
	public NoteResult<Object> changePassword(String userId, String password,
			String newpassword) {
		  //查询数据库密码与原密码对比（需要通过加密过程）
		
		User user = userDao.findByUserId(userId);
		String miPassword = NoteUtil.md5(password);
		  //构建返回结果
		NoteResult<Object> result= new NoteResult<Object>();
		if(!miPassword.equals(user.getCn_user_password())){//原密码错误
			System.out.println("输入原密码："+miPassword);
			System.out.println("数据库加密密码:"+user.getCn_user_password());
			result.setStatus(1);
			result.setMsg("原密码错误");
			return result;
		}
		user.setCn_user_id(userId);
		user.setCn_user_password(NoteUtil.md5(newpassword));
	    int rows=userDao.updatePassword(user);//执行加密后修改
      	if(rows!=1){
      		result.setStatus(2);
			result.setMsg("修改密码错误");
			return result;
      	}	
      	result.setStatus(0);
		result.setMsg("修改密码成功");
		return result;
	}

}
