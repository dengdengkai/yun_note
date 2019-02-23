package com.cloud_note.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cloud_note.entity.Note;
import com.cloud_note.util.NoteResult;

@Repository("noteDAO")
public interface NoteDAO {
	//��ѯ�����ͬ�Ĳ���ʱ������Ҳ��ͬʱ���Բ���ע�⣬sql�ļ���#{}�е��ֶ���Param�����еķֱ�һ��
	// public List<Note> findByBookId(@Param("id")String bookId,@Param("status") String status);
    //����ʱ�򣬲����ֶο��Բ�һ�µ���˳������Ӧ
	
	//����  ����һ
	public List<Note> findByBookId(@Param("bookid")String bookid,@Param("status")String status);
      //������
	 //����bookid��ѯnote
      public List<Map> findByBookId2(String bookId);
      //����noteid��ѯnote
      public Note findByNoteId(String noteId);
      
      //����noteId���� �������Ϳ���Ϊvoid ��int����Ӱ�������
      public int updateNote(Note note);
      
      //���ӱʼ�
      public int  save(Note note);
      
      public int deleteNote(Note note);
      
      
      public void updateType(Note note);
      
      //���Զ�̬SQL,��������
      public void updateNoteByMap(Map<String,Object> map);
      //��������foreach  SQL
      /**
       * map����Ҫ�����������:
       * map={ids:[id1,id2,id3...],
       *      state:2}
       *      ids����ɾ���ıʼ��б�
       *      status ����ɾ���ʼǵ� ״̬����
       *      
       *      
       * @param map
       * @return
       */
      public  int deleteNotes(Map<String,Object> map);
      
      public int deleteNote1(String id);
}
