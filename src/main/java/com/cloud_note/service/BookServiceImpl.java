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
		  //�������ؽ��result
		  NoteResult<List<Book>> result = new NoteResult<List<Book>>(); 
		     result.setStatus(0);
		     result.setMsg("��ѯ�ʼǱ��ɹ�");
		     result.setData(books);
		return result;
	}
 //���ӱʼǱ�
	public NoteResult<Book> addBook(String userId, String BookName) {
		Book book = new Book();
		//�����½��ʼǱ���Ϣ
		book.setCn_user_id(userId);
		book.setCn_notebook_id(NoteUtil.createdId());
		book.setCn_notebook_name(BookName);
		book.setCn_notebook_type_id("5");
		book.setCn_notebook_createtime(NoteUtil.getNowTimestamp());

		//���ӱʼǱ�
		int n = bookDAO.save(book);
		
		//����result
		 NoteResult<Book> result= new NoteResult<Book>();
		if(n<=0){
			result.setStatus(1);
			result.setMsg("��ӱʼǱ�ʧ�ܣ�");
			return result;
		}	
		else{
			result.setStatus(0);
			result.setMsg("��ӱʼǱ��ɹ���");
			result.setData(book);
			return result;
		}
				
	}

}
