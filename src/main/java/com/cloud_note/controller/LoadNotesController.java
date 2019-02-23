package com.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.service.NoteService;
import com.cloud_note.util.NoteResult;

@Controller("loadNotesController") //必须配置这个不然找
//不到处理请求的
@RequestMapping("/note")
public class LoadNotesController {
    @Resource(name="noteService")
	private NoteService service;   
	
    @RequestMapping("/loadnotes.do")
    @ResponseBody
	public NoteResult<List<Map>> excute(String bookId){
    	   NoteResult<List<Map>> result =
    			      service.loadBookNotes(bookId);
    	   System.out.println("获取的bookid是："+bookId);
    System.out.println(result.getStatus()+","+
      			 result.getMsg());
    System.out.println("获取的data是是："+result.getData());
      	 for(Map note:result.getData()){
      		 System.out.println(note.get("cn_note_id")
      				 +","
      				 +note.get("cn_note_title"));
      	 }
    	   return result;
       }
}
