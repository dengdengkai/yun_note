package com.cloud_note.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cloud_note.entity.Note;
import com.cloud_note.util.NoteResult;

@Repository("noteDAO")
public interface NoteDAO {
	//查询多个不同的参数时，类型也不同时可以采用注解，sql文件的#{}中的字段与Param括号中的分别一致
	// public List<Note> findByBookId(@Param("id")String bookId,@Param("status") String status);
    //其余时候，参数字段可以不一致但是顺序必须对应
	
	//测试  方法一
	public List<Note> findByBookId(@Param("bookid")String bookid,@Param("status")String status);
      //方法二
	 //依据bookid查询note
      public List<Map> findByBookId2(String bookId);
      //依据noteid查询note
      public Note findByNoteId(String noteId);
      
      //依据noteId更新 返回类型可以为void 或int返回影响的行数
      public int updateNote(Note note);
      
      //增加笔记
      public int  save(Note note);
      
      public int deleteNote(Note note);
      
      
      public void updateType(Note note);
      
      //测试动态SQL,各种条件
      public void updateNoteByMap(Map<String,Object> map);
      //测试批量foreach  SQL
      /**
       * map中需要添加两个参数:
       * map={ids:[id1,id2,id3...],
       *      state:2}
       *      ids代表被删除的笔记列表
       *      status 代表被删除笔记的 状态属性
       *      
       *      
       * @param map
       * @return
       */
      public  int deleteNotes(Map<String,Object> map);
      
      public int deleteNote1(String id);
}
