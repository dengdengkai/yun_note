package com.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud_note.entity.Book;
import com.cloud_note.service.BookService;
import com.cloud_note.util.NoteResult;

@Controller("addBookController")
public class AddBookController {
   
	@Resource(name="bookService")
	private BookService  service;
	
	@RequestMapping("/book/add.do")
	@ResponseBody
	public NoteResult<Book> excute(String userId,String bookName){
		//测试
		System.out.println("获取增加笔记本信息:"+"userId:"+userId+
				  "\n bookName:"+bookName);
		NoteResult<Book> result = service.addBook(userId, bookName);
		 System.out.println("返回信息：\n"+result);
		return result;
	}
}
