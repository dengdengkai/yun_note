package com.cloud_note.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cloud_note.entity.Book;

@Repository("bookDAO")
public interface BookDAO {
	//һ���û���Ӧ���book,����ʹ��list
	public List<Book> findByUserId(String userId);
	
	public int save(Book book);

}
