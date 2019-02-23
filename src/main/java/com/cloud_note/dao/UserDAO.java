package com.cloud_note.dao;

import org.springframework.stereotype.Repository;

import com.cloud_note.entity.User;

@Repository("userDAO")
public interface UserDAO {
	 
	public User findByName(String name);//登录方法
	public void save(User user); //注册方法
	public User findByUserId(String userId);//查找用户
	public int updatePassword(User user); //修改密码
	
	
}
