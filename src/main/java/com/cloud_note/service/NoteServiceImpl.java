package com.cloud_note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud_note.dao.NoteDAO;
import com.cloud_note.entity.Note;
import com.cloud_note.util.NoteResult;
import com.cloud_note.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService{

    @Resource(name="noteDAO")
    private NoteDAO dao;
	
  public NoteResult<List<Map>> loadBookNotes(String bookId) {
		List<Map> notes = dao.findByBookId2(bookId);
		//����result
		NoteResult<List<Map>> result
		          = new NoteResult<List<Map>>();
		result.setData(notes);
		result.setStatus(0);
		result.setMsg("���رʼǱ��ɹ�");
		return result;
	}


public NoteResult<Note> loadnote(String noteId) {
     Note note= dao.findByNoteId(noteId);
     NoteResult<Note> result1
                    = new NoteResult<Note>();
     
     if(note==null)
     {
   /*
    *  list��=null  ��  �� list.isEmpty()��ʲô����?

               ����൱�룬��ҪҪ���̵�����
       list��=null         �����ж��Ƿ����̵�
               ��list.isEmpty()     û���ж��̵��Ƿ���ڣ������ж��̵��Ƿ��ж���
                �ܽ��÷���������̵궼û�У������ĵĶ�������
    */
      result1.setStatus(1);
	  result1.setMsg("δ�ҵ�����");
	  return result1;
      }     
     else
     {
         result1.setData(note);
         result1.setStatus(0);
   	     result1.setMsg("���رʼǳɹ�");
   		return result1;
       }


}


//������±ʼ�

public NoteResult<Object> updateNote(String noteId, String title, String body) {
	Note note= new Note();
	note.setCn_note_id(noteId);
	note.setCn_note_title(title);
	note.setCn_note_body(body);
	 Long time = System.currentTimeMillis();
	    note.setCn_note_last_modify_time(time);
	 
	    //�������ݿ��¼
	   int rows = dao.updateNote(note);
	//����result
	NoteResult<Object> result  = new NoteResult<Object>();
	if(rows==1){
	   result.setStatus(0);
	   result.setMsg("����ʼǳɹ���");
	   return result;
	}
	else{
		   result.setStatus(1);
		   result.setMsg("����ʼ�ʧ�ܣ�");
		   return result;
		}
	
	
}


//�½��ʼ�
public NoteResult<Note> addNote(String userId, String bookId, String title) {
	Note note = new Note();
	//�û�ID
	note.setCn_user_id(userId);
	//�ʼǱ�ID
	note.setCn_notebook_id(bookId);
	//�ʼǱ���
	note.setCn_note_title(title);
	//�ʼ�Id
	note.setCn_note_id(NoteUtil.createdId());
	//�ʼ�����
	note.setCn_note_body(null);
	//����ʱ��
	note.setCn_note_create_time(System.currentTimeMillis());
	//����޸�ʱ��
	note.setCn_note_last_modify_time(System.currentTimeMillis());
	//����״̬1-normal 2-delete
	note.setCn_note_status_id("1");
	//���ͣ� 1-normal 2-favor 3-share
	note.setCn_note_type_id("1");
	
	
	int rows= dao.save(note);
	//�������
	NoteResult<Note> result = new NoteResult<Note>();
	if(rows<=0){
		result.setStatus(1);
		result.setMsg("�����ʼ�ʧ��");
		return result;
		
	}else{
		result.setStatus(0);
		result.setMsg("�����ʼǳɹ�");
		result.setData(note);
		return result;
	 }
	
	 }


//ɾ���ʼ�
public NoteResult<Note> deleteNote(String noteId) {
	
	Note note = new Note();
	note.setCn_note_id(noteId);
	note.setCn_note_status_id("2");
	note.setCn_note_last_modify_time(System.currentTimeMillis());
	//ִ�и���
	int rows = dao.deleteNote(note);
	NoteResult<Note> result = new NoteResult<Note>();
	if(rows <= 0){
		result.setStatus(1);
		result.setMsg("ɾ���ʼ�ʧ�ܣ�");
		
	}
	else {
		result.setStatus(0);
		result.setMsg("ɾ���ʼǳɹ���");
	}
	return result  ;
}


@Transactional
public void deleteNotes(String... ids) {
	//String... ����String[] 
	for(String id : ids){
	  int n = dao.deleteNote1(id);
	  if(n!=1){
		  //�׳��쳣����������Ļع�
		  throw new RuntimeException("ɾ����");
	  }
	}
	
}


      
}
