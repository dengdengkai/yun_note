package com.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud_note.dao.BookDAO;
import com.cloud_note.entity.Book;
import com.cloud_note.util.NoteResult;
import com.cloud_note.util.NoteUtil;

@Service("bookService")
public class BookServiceImpl implements BookService{

    @Resource(name="bookDAO")
	private BookDAO bookDAO;
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		  List<Book>  books = bookDAO.findByUserId(userId);
		  //构建返回结果result
		  NoteResult<List<Book>> result = new NoteResult<List<Book>>(); 
		     result.setStatus(0);
		     result.setMsg("查询笔记本成功");
		     result.setData(books);
		return result;
	}
 //增加笔记本
	public NoteResult<Book> addBook(String userId, String BookName) {
		Book book = new Book();
		//设置新建笔记本信息
		book.setCn_user_id(userId);
		book.setCn_notebook_id(NoteUtil.createdId());
		book.setCn_notebook_name(BookName);
		book.setCn_notebook_type_id("5");
		book.setCn_notebook_createtime(NoteUtil.getNowTimestamp());

		//增加笔记本
		int n = bookDAO.save(book);
		
		//构建result
		 NoteResult<Book> result= new NoteResult<Book>();
		if(n<=0){
			result.setStatus(1);
			result.setMsg("添加笔记本失败！");
			return result;
		}	
		else{
			result.setStatus(0);
			result.setMsg("添加笔记本成功！");
			result.setData(book);
			return result;
		}
				
	}

}
