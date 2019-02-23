package com.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.Book;
import com.cloud_note.service.BookService;
import com.cloud_note.util.NoteResult;




@Controller("loadBooksController")
public class LoadBooksController {
	
	@Resource(name="bookService")
	private BookService bookService;
	
	@RequestMapping("/book/loadBooks.do")
	@ResponseBody
	public NoteResult<List<Book>> excute(String userId){
		NoteResult<List<Book>> result =
				 bookService.loadUserBooks(userId);
		System.out.println(userId);
		System.out.println(result);
		return result;
	}
}
