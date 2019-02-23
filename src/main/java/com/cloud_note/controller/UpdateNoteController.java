package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.Note;
import com.cloud_note.service.NoteService;
import com.cloud_note.util.NoteResult;

@Controller("updateNoteController")
public class UpdateNoteController {
    
	@Resource(name="noteService")
	private NoteService service;
	
	@RequestMapping("/note/update.do")
	@ResponseBody
	public NoteResult<Object> excute(String noteId,String title,String body){
		
		System.out.println("noteId:"+noteId+"\n"
				             +"title:"+title+"\n"
				             +"body:"+body+"\n");
		 NoteResult<Object> result = 
				 service.updateNote(noteId, title, body);
		return result;
	}
}
