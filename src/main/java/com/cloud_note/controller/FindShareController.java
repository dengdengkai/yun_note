package com.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.Share;
import com.cloud_note.service.ShareService;
import com.cloud_note.util.NoteResult;

@Controller("findShareController")
public class FindShareController {
       
	@Resource(name="noteShareService")
	  private ShareService service;
	
	@RequestMapping("/share/search.do")
	@ResponseBody
	public NoteResult<List<Share>> excute(String keyword,int page){
		System.out.println("获取查找分享的关键字："+keyword);
		NoteResult<List<Share>> result =
				 service.findNote(keyword,page);
		System.out.println("查找分享的结果是："+result);
		
		return result;
	}
}
