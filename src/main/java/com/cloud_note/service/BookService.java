package com.cloud_note.service;

import java.util.List;

import com.cloud_note.entity.Book;
import com.cloud_note.util.NoteResult;

public interface BookService {
    //加载笔记本 
	public NoteResult<List<Book>> loadUserBooks(
    		               String userId);
	
	public NoteResult<Book> addBook(String userId,String BookName);
}
