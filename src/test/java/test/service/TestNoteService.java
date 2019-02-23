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
     
     //ע���·����ַ�ʽ��д����ȡ�ȣ�List<Note> ,list<Map>
     @Test
     //����service
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
     //����service���رʼǾ�����Ϣ
     public void test2(){
    	 
    	 String noteId="051538a6-0f8e-472c-8765-251a795bc88f";
    	 // �����Ҳ���
    	 //String noteId="SCA";
    	 NoteResult<Note> result =
    			 service.loadnote(noteId);
    	 System.out.println(result.getStatus()+"\n"+
    			 result.getMsg());

    		 System.out.println(result.getData());
    	
     }
     
     @Test
     //����service����ʼǾ�����Ϣ
     public void test3(){
    	 
       String noteId="051538a6-0f8e-472c-8765-251a795bc88f";
    
    	
    	 NoteResult<Object> result =
    			 service.updateNote(noteId, "�޸ĺú�","lueluelue");
    	 System.out.println(result);
    	
     }
     @Test
     //����service����ʼǾ�����Ϣ
     public void test4_deleteNote(){
    	 
       String noteId="051538a6-0f8e-472c-8765-251a795bc88f";
    
    	
    	 NoteResult<Note> result =
    			 service.deleteNote(noteId);
    	 System.out.println(result);
    	
     }
     
     @Test
     //����������@Transactional
     public void testDeleteNote(){
    	 //���ö�̬����ʱ�򣬿��Բ��������飬ֱ��д����
    	 //String[] ids={"id1","id2"};
    	 //service.deleteNotes(ids);
    	 
    	 //����...�ô��ǽ��·��Ĳ����ڱ���������������
         service.deleteNotes("30c5af5c3bcd4899872c041a88f3c02b","id2","id3");
         
     }
}
