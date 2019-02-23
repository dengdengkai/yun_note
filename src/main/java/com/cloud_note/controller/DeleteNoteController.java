package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.Note;
import com.cloud_note.service.NoteService;
import com.cloud_note.util.NoteResult;

@Controller("deleteNoteController")
public class DeleteNoteController {
       @Resource(name="noteService")
	   private NoteService service;
       
       
       @RequestMapping("/note/delete.do")
       @ResponseBody
	   public NoteResult<Note> excute(String noteId){
    	  System.out.println("获取删除的笔记ID"+noteId);
    	   NoteResult<Note> result =
    			          service.deleteNote(noteId);
    	   System.out.println(result);
    	   return result;
	   }
}
