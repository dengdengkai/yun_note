package com.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.Note;
import com.cloud_note.service.NoteService;
import com.cloud_note.util.NoteResult;


@Controller("loadnoteController")
@RequestMapping("/note")
public class LoadNoteController {
          
	@Resource(name="noteService")
	private NoteService service;
	
	//@RequestMapping("/note/load.do")或者分开写也可以
	@RequestMapping("/load.do")
	@ResponseBody
	public NoteResult<Note> excute(String noteId){
        	System.out.println("成功获取noteId:"+noteId);
		
		NoteResult<Note> result =
				service.loadnote(noteId);
		System.out.println("成功返回："+result);
				
				return result;
          }
}
