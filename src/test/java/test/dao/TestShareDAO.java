package test.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import basetest.TestBase;

import com.cloud_note.dao.ShareDAO;
import com.cloud_note.entity.Share;
import com.cloud_note.util.NoteUtil;

public class TestShareDAO extends TestBase{
     
	private ShareDAO dao;
	@Before
	public void init(){
		ApplicationContext ac =super.getContext();
		dao = ac.getBean("shareDAO",ShareDAO.class);
	}
	@Test
	public void test_addShare(){
		Share share = new Share();
		share.setCn_note_id("666");
		share.setCn_share_id(NoteUtil.createdId());
		share.setCn_share_title("分享测试1");
		share.setCn_share_body("测试插入分享");
		
		dao.save(share);
		
	}
	
}
