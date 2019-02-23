package com.cloud_note.service;

import com.cloud_note.entity.User;
import com.cloud_note.util.NoteResult;

public interface UserService {
    //µÇÂ¼ 
	public NoteResult<User> checkLogin(
    		   String name,String password);
   //×¢²á
	public NoteResult<User> addUser(
			String name,String password,String nick);
	//ÐÞ¸ÄÃÜÂë
		public NoteResult<Object> changePassword(
				String userId,String password,String newpassword);
	
}
