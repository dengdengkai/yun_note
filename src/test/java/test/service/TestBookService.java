package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basetest.TestBase;

import com.cloud_note.entity.Book;
import com.cloud_note.service.BookService;
import com.cloud_note.util.NoteResult;

public class TestBookService extends TestBase{
	private BookService service;
	@Before
    public void init(){
 	
 	  ApplicationContext ac = super.getContext();
      service = ac.getBean("bookService",BookService.class);
 			  
    }
	@Test
	//²âÊÔ¼ÓÔØBook
	public void test1(){
		String userId="03590914-a934-4da9-ba4d-b41799f917d1";
		NoteResult<List<Book>> result = service.loadUserBooks(userId);
	    System.out.println(result.getMsg());
	    System.out.println("×´Ì¬£º"+result.getStatus());
	    for(Book book : result.getData())
	    {
	    	System.out.println(book);
	    }
	}
	
	@Test
	//²âÊÔÐÂ½¨±Ê¼Ç±¾
	public void test_addBook(){
		String userId="03590914-a934-4da9-ba4d-b41799f917d1";
		NoteResult<Book> result = service.addBook(userId, "helloBook");
	    System.out.println(result.getMsg());
	    System.out.println("×´Ì¬£º"+result.getStatus()+"\n"
	                          +result.getData());
	  
	}
}
