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
		//构建result
		NoteResult<List<Map>> result
		          = new NoteResult<List<Map>>();
		result.setData(notes);
		result.setStatus(0);
		result.setMsg("加载笔记本成功");
		return result;
	}


public NoteResult<Note> loadnote(String noteId) {
     Note note= dao.findByNoteId(noteId);
     NoteResult<Note> result1
                    = new NoteResult<Note>();
     
     if(note==null)
     {
   /*
    *  list！=null  跟  ！ list.isEmpty()有什么区别?

               这就相当与，你要要到商店买东西
       list！=null         首先判断是否有商店
               ！list.isEmpty()     没有判断商店是否存在，而是判断商店是否有东西
                总结用法：如果连商店都没有，何来的的东西可卖
    */
      result1.setStatus(1);
	  result1.setMsg("未找到数据");
	  return result1;
      }     
     else
     {
         result1.setData(note);
         result1.setStatus(0);
   	     result1.setMsg("加载笔记成功");
   		return result1;
       }


}


//保存更新笔记

public NoteResult<Object> updateNote(String noteId, String title, String body) {
	Note note= new Note();
	note.setCn_note_id(noteId);
	note.setCn_note_title(title);
	note.setCn_note_body(body);
	 Long time = System.currentTimeMillis();
	    note.setCn_note_last_modify_time(time);
	 
	    //更新数据库记录
	   int rows = dao.updateNote(note);
	//构建result
	NoteResult<Object> result  = new NoteResult<Object>();
	if(rows==1){
	   result.setStatus(0);
	   result.setMsg("保存笔记成功！");
	   return result;
	}
	else{
		   result.setStatus(1);
		   result.setMsg("保存笔记失败！");
		   return result;
		}
	
	
}


//新建笔记
public NoteResult<Note> addNote(String userId, String bookId, String title) {
	Note note = new Note();
	//用户ID
	note.setCn_user_id(userId);
	//笔记本ID
	note.setCn_notebook_id(bookId);
	//笔记标题
	note.setCn_note_title(title);
	//笔记Id
	note.setCn_note_id(NoteUtil.createdId());
	//笔记内容
	note.setCn_note_body(null);
	//创建时间
	note.setCn_note_create_time(System.currentTimeMillis());
	//最后修改时间
	note.setCn_note_last_modify_time(System.currentTimeMillis());
	//设置状态1-normal 2-delete
	note.setCn_note_status_id("1");
	//类型： 1-normal 2-favor 3-share
	note.setCn_note_type_id("1");
	
	
	int rows= dao.save(note);
	//构建结果
	NoteResult<Note> result = new NoteResult<Note>();
	if(rows<=0){
		result.setStatus(1);
		result.setMsg("创建笔记失败");
		return result;
		
	}else{
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(note);
		return result;
	 }
	
	 }


//删除笔记
public NoteResult<Note> deleteNote(String noteId) {
	
	Note note = new Note();
	note.setCn_note_id(noteId);
	note.setCn_note_status_id("2");
	note.setCn_note_last_modify_time(System.currentTimeMillis());
	//执行更新
	int rows = dao.deleteNote(note);
	NoteResult<Note> result = new NoteResult<Note>();
	if(rows <= 0){
		result.setStatus(1);
		result.setMsg("删除笔记失败！");
		
	}
	else {
		result.setStatus(0);
		result.setMsg("删除笔记成功！");
	}
	return result  ;
}


@Transactional
public void deleteNotes(String... ids) {
	//String... 就是String[] 
	for(String id : ids){
	  int n = dao.deleteNote1(id);
	  if(n!=1){
		  //抛出异常触发，事务的回滚
		  throw new RuntimeException("删错了");
	  }
	}
	
}


      
}
