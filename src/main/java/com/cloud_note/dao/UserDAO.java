package com.cloud_note.dao;

import org.springframework.stereotype.Repository;

import com.cloud_note.entity.User;

@Repository("userDAO")
public interface UserDAO {
	 
	public User findByName(String name);//��¼����
	public void save(User user); //ע�᷽��
	public User findByUserId(String userId);//�����û�
	public int updatePassword(User user); //�޸�����
	
	
}
