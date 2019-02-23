package com.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.service.NoteService;
import com.cloud_note.util.NoteResult;

@Controller("loadNotesController") //�������������Ȼ��
//�������������
@RequestMapping("/note")
public class LoadNotesController {
    @Resource(name="noteService")
	private NoteService service;   
	
    @RequestMapping("/loadnotes.do")
    @ResponseBody
	public NoteResult<List<Map>> excute(String bookId){
    	   NoteResult<List<Map>> result =
    			      service.loadBookNotes(bookId);
    	   System.out.println("��ȡ��bookid�ǣ�"+bookId);
    System.out.println(result.getStatus()+","+
      			 result.getMsg());
    System.out.println("��ȡ��data���ǣ�"+result.getData());
      	 for(Map note:result.getData()){
      		 System.out.println(note.get("cn_note_id")
      				 +","
      				 +note.get("cn_note_title"));
      	 }
    	   return result;
       }
}
