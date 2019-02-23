package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import basetest.TestBase;

import com.cloud_note.entity.Note;
import com.cloud_note.service.ShareService;
import com.cloud_note.util.NoteResult;

public class TestShareService extends TestBase{
        
	private ShareService service;
	    @Before
	    public void init(){
	    	ApplicationContext ac = super.getContext();
	    	service = ac.getBean("shareService",ShareService.class);
	    	
	    }
	    @Test
	    public void Test_ShareService(){
	    	NoteResult<Note> result =
	    			service.share("051538a6-0f8e-472c-8765-251a795bc88f");
	    	System.out.println(result);
	    }
}
