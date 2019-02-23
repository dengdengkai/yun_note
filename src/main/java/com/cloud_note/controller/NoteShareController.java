package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.Note;
import com.cloud_note.service.ShareService;
import com.cloud_note.util.NoteResult;

@Controller("noteShareController")
public class NoteShareController {
        
	@Resource(name="noteShareService")
	private ShareService service;
   
	
	@RequestMapping("/share/add.do")
	@ResponseBody
    public NoteResult<Note> excute(String noteId){
    	System.out.println("获得分享的ID"+noteId);
    	 NoteResult<Note> result = 
    			 service.share(noteId);
    	return result;
    }
}
