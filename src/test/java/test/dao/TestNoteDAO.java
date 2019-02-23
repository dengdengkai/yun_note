package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import basetest.TestBase;

import com.cloud_note.dao.NoteDAO;
import com.cloud_note.entity.Note;

public class TestNoteDAO extends TestBase{
       
	private NoteDAO noteDao;
	@Before
	public void init(){
		ApplicationContext ac 
		            = super.getContext();
		noteDao = ac.getBean("noteDAO",NoteDAO.class);
	}
	@Test
	//测试笔记持久层，依据book和status查询
	public void test1_find_Notes(){
		String cn_notebook_id="20b4cbec-bd55-4c21-9c41-3a11ada2b803";
	
		 List<Map> notes =
				  noteDao.findByBookId2(cn_notebook_id);
		for(Map note : notes){
			   System.out.println(note.get("cn_note_id")+","+
		                         note.get("cn_note_title"));

		}
	}
	@Test
	//测试笔记持久层2，依据bookId和status查询
	public void test2(){
		String bookId="20b4cbec-bd55-4c21-9c41-3a11ada2b803";
	
		 List<Note> notes =
				  noteDao.findByBookId(bookId,"1");
		for(Note note : notes){
			   System.out.println(note);
		}
	}
	@Test
	//测试笔记持久层2，依据noteId和status查询title,body
	public void test3_findNote(){
		String noteId="051538a6-0f8e-472c-8765-251a795bc88f";
	//
		//String noteId="0";
		
		 Note note =
				  noteDao.findByNoteId(noteId);
		 System.out.println(note);
	
			   System.out.println(note.getCn_note_title());
			   System.out.println(note.getCn_note_body());
			   System.out.println(note.getCn_note_last_modify_time());
		}
	
	
	
	@Test
	//测试笔记持久层2，依据noteId修改note
	public void test4_updateNote(){
		String noteId="73c8d32e-0ed4-424a-af46-22f00dbf707f";
		Note newnote = new Note();
		newnote.setCn_note_id(noteId);
		newnote.setCn_note_title("修改");
		newnote.setCn_note_body("修改测试。");
	    Long time = System.currentTimeMillis();
	    newnote.setCn_note_last_modify_time(time);
		//执行修改
	  int n   =	noteDao.updateNote(newnote);
	  System.out.println("是受到影响的行数："+n);
		Note oldnote = noteDao.findByNoteId(noteId);
	
		
		
		System.out.println("标题:"+oldnote.getCn_note_title()+
				           "\n内容:"+oldnote.getCn_note_body()+
				           "\n修改时间:"+oldnote.getCn_note_last_modify_time());

	}
		 @Test
		 //测试插入操作
	public void test5_InsertNote(){
		String noteId="73c8d32e-0ed4-424a-af46-22f00dbf707f";
		Note newnote = new Note();
		newnote.setCn_note_id(noteId);
		newnote.setCn_note_title("插入");
		newnote.setCn_note_body("插入测试。");
	    Long time = System.currentTimeMillis();
	    newnote.setCn_note_last_modify_time(time);
		//执行修改
	  int n   =	noteDao.save(newnote);
	  System.out.println("是受到影响的行数："+n);
	Note note = noteDao.findByNoteId(noteId);
	
		
		
		System.out.println("标题:"+note.getCn_note_title()+
				           "\n内容:"+note.getCn_note_body()+
				           "\n修改时间:"+note.getCn_note_last_modify_time());
	
	}
		 @Test
		 //测试插入操作
	public void test6_deleteNote(){
		String noteId="73c8d32e-0ed4-424a-af46-22f00dbf707f";
		Note newnote = new Note();
		newnote.setCn_note_id(noteId);
		newnote.setCn_note_status_id("2");
	    Long time = System.currentTimeMillis();
	    newnote.setCn_note_last_modify_time(time);
		//执行修改
	  int n   =	noteDao.deleteNote(newnote);
	  System.out.println("是受到影响的行数："+n);
	
	
	}
		 
		 @Test
		 public void testUpdateNoteByMap(){
			 Map<String, Object> map =
					 new HashMap<String,Object>();
			 map.put("title", "java");
			 map.put("body","body");
			
			 map.put("noteId", "73c8d32e-0ed4-424a-af46-22f00dbf707f");
			 //故意省略参数body 和 time
			 noteDao.updateNoteByMap(map);
		 }
		 
		 
		 @Test
		 public void testDeleteNotes(){
			 Map<String, Object> map =
					 new HashMap<String,Object>();
			 String[] ids={"881a51535a7a456c83d64a23e0c189ce",
					 "db239876bc5843c7abe5e2e2eadded6a",
					 "2d1e22d020c2477c8549a052922caf9f"};
			 map.put("ids", ids);
			 map.put("status",1);
			 int n = noteDao.deleteNotes(map);
			
			System.out.println(n);
		 }
}
