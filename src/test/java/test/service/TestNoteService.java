package test.service;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import basetest.TestBase;

import com.cloud_note.entity.Note;
import com.cloud_note.service.NoteService;
import com.cloud_note.util.NoteResult;

public class TestNoteService extends TestBase{
     private NoteService service;
     @Before
     public void init(){
    	 ApplicationContext ac =
    			  super.getContext();
    	 service = ac.getBean("noteService",NoteService.class);
    	 
    	 
     }
     
     //注意下方两种方式的写法获取等，List<Note> ,list<Map>
     @Test
     //测试service
     public void test1(){
    	 String bookId="20b4cbec-bd55-4c21-9c41-3a11ada2b803";
    	 NoteResult<List<Map>> result =
    			 service.loadBookNotes(bookId);
    	 System.out.println(result.getStatus()+"\n"+
    			 result.getMsg());
    	 for(Map note:result.getData()){
    		 System.out.println(note.get("cn_note_id")
    				 +","
    				 +note.get("cn_note_title"));
    	 }
     }
     
     @Test
     //测试service加载笔记具体信息
     public void test2(){
    	 
    	 String noteId="051538a6-0f8e-472c-8765-251a795bc88f";
    	 // 而是找不到
    	 //String noteId="SCA";
    	 NoteResult<Note> result =
    			 service.loadnote(noteId);
    	 System.out.println(result.getStatus()+"\n"+
    			 result.getMsg());

    		 System.out.println(result.getData());
    	
     }
     
     @Test
     //测试service保存笔记具体信息
     public void test3(){
    	 
       String noteId="051538a6-0f8e-472c-8765-251a795bc88f";
    
    	
    	 NoteResult<Object> result =
    			 service.updateNote(noteId, "修改好好","lueluelue");
    	 System.out.println(result);
    	
     }
     @Test
     //测试service保存笔记具体信息
     public void test4_deleteNote(){
    	 
       String noteId="051538a6-0f8e-472c-8765-251a795bc88f";
    
    	
    	 NoteResult<Note> result =
    			 service.deleteNote(noteId);
    	 System.out.println(result);
    	
     }
     
     @Test
     //测试事务标记@Transactional
     public void testDeleteNote(){
    	 //调用动态参数时候，可以不创建数组，直接写参数
    	 //String[] ids={"id1","id2"};
    	 //service.deleteNotes(ids);
    	 
    	 //下面...好处是讲下方的参数在编译器里面变成数组
         service.deleteNotes("30c5af5c3bcd4899872c041a88f3c02b","id2","id3");
         
     }
}
