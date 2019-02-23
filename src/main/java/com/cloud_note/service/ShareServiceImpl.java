package com.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud_note.dao.NoteDAO;
import com.cloud_note.dao.ShareDAO;
import com.cloud_note.entity.Note;
import com.cloud_note.entity.Share;
import com.cloud_note.util.NoteResult;
import com.cloud_note.util.NoteUtil;

@Service("noteShareService")
@Transactional
public class ShareServiceImpl implements ShareService{

	@Resource(name="noteDAO")
	private NoteDAO noteDao;
	
	
	@Resource(name="shareDAO")
	private ShareDAO shareDao;
	
    //分享笔记，1.先查询，再做分享
	public NoteResult<Note> share(String noteId) {
		//构建result
	    NoteResult<Note> result = new NoteResult<Note>();
		Note note = noteDao.findByNoteId(noteId);
		if("2".equals(note.getCn_note_type_id())){
			result.setStatus(1);
			result.setMsg("该笔记已分享过");
			return result;
		}
		Note note1 = new Note();
		note1.setCn_note_id(noteId);
		note1.setCn_note_type_id("2");
		noteDao.updateType(note1);
		//添加到分享列表
		Share share = new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_id(NoteUtil.createdId());//主键
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
	     //保存分享记录
	      shareDao.save(share);
	//模拟异常
	      /*
	      String str=null;
	      str.length();
	      */
	    	result.setStatus(0);
	    	result.setMsg("分享成功！");
	    	result.setData(note);
	    
		
		return result;
	}


	public NoteResult<List<Share>> findNote(String keyword,int page) {
		   String title="%"+keyword+"%";
		   int begin=(page-1)*3;//计算抓取记录的起点
		   Map<String, Object> params = new HashMap();
		   params.put("title", title);
		   params.put("begin", begin);
		 //模糊查询
		   List<Share> shares = shareDao.findLikeTitle(params);
		  //构建返回结果
		   NoteResult<List<Share>> result = new NoteResult<List<Share>>();
		   result.setStatus(0);
		   result.setMsg("搜索完成");
		   result.setData(shares);
		   return result;
	}

}
