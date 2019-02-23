package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.Note;
import com.cloud_note.service.NoteService;
import com.cloud_note.util.NoteResult;

@RequestMapping("/note")
@Controller("addNoteController")
public class AddNoteController {
   @Resource(name="noteService")
	private NoteService service;
   
   @RequestMapping("/add.do")
   @ResponseBody
   public NoteResult<Note> excute(String userId,
		                          String bookId,
		                          String title){
	   
	   System.out.println("后台获取数据："+userId+","+bookId+","+title);
	   NoteResult<Note> result = service.addNote(userId, bookId, title);
	   
	   return result;
	   
   }
}
