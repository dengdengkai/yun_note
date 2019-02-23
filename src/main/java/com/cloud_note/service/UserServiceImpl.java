package com.cloud_note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud_note.dao.UserDAO;
import com.cloud_note.entity.User;
import com.cloud_note.util.NoteResult;
import com.cloud_note.util.NoteUtil;

@Service("userService")
//ɨ���spring����
@Transactional
public class UserServiceImpl implements UserService{

	
	@Resource(name="userDAO")
	private UserDAO userDao;
	
	//��¼
	public NoteResult<User> checkLogin(String name, String password) {
		//���ս������  
		NoteResult<User> result =
				   new NoteResult<User>();
		//������name��ѯ���ݿ⣨service�����dao�㣩
		User user = userDao.findByName(name);
		//����û�
		if(user == null){
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//�������
		//�Ƚ���md5����Ȼ�������ݿ���ע��ʱ
		 //�����ܵ�����Ա�
		String md5Password
		      = NoteUtil.md5(password);
				 
		if(!user.getCn_user_password()
				.equals(md5Password)){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
			
		}
		//�û��������붼��ȷ
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		result.setData(user);
		return result;
	}

	//ע��
	public NoteResult<User> addUser(String name, String password, String nick) {
		NoteResult<User> result =
				new NoteResult<User>();
		User user = userDao.findByName(name);
	    
		//�û���ռ�ü��
		if(user != null)
		{
			result.setStatus(1);
			result.setMsg("�û����Ѵ���");
		   return result;	
		}
		//�û���û�б�ռ��
		user = new User();
		user.setCn_user_id(NoteUtil.createdId()); //����NoteUtil��ľ�̬������ȡid����������id
		user.setCn_user_name(name);
		user.setCn_user_password(NoteUtil.md5(password));//�������벢����
		user.setCn_user_nick(nick);
		user.setCn_user_token(null);
		
		userDao.save(user);//����dao�������ݿ�����û�����
		
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
	
		return result;
	}

	
	public NoteResult<Object> changePassword(String userId, String password,
			String newpassword) {
		  //��ѯ���ݿ�������ԭ����Աȣ���Ҫͨ�����ܹ��̣�
		
		User user = userDao.findByUserId(userId);
		String miPassword = NoteUtil.md5(password);
		  //�������ؽ��
		NoteResult<Object> result= new NoteResult<Object>();
		if(!miPassword.equals(user.getCn_user_password())){//ԭ�������
			System.out.println("����ԭ���룺"+miPassword);
			System.out.println("���ݿ��������:"+user.getCn_user_password());
			result.setStatus(1);
			result.setMsg("ԭ�������");
			return result;
		}
		user.setCn_user_id(userId);
		user.setCn_user_password(NoteUtil.md5(newpassword));
	    int rows=userDao.updatePassword(user);//ִ�м��ܺ��޸�
      	if(rows!=1){
      		result.setStatus(2);
			result.setMsg("�޸��������");
			return result;
      	}	
      	result.setStatus(0);
		result.setMsg("�޸�����ɹ�");
		return result;
	}

}
