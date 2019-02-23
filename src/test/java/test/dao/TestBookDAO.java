package test.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud_note.dao.BookDAO;
import com.cloud_note.entity.Book;


public class TestBookDAO {
	  private BookDAO dao;
      @Before
      public void init(){
    	  String[] confs={"/conf/spring-mybatis.xml","/conf/spring-mvc.xml"};
   	  //上述配置文件可以像测试service层一样写一个抽象类提取出去
    	  ApplicationContext ac = new
   			   ClassPathXmlApplicationContext(confs);
   	   dao = ac.getBean("bookDAO",BookDAO.class);
   			  
      }
      @Test
      //测试找在cn_notebook中找userid
      public void test1(){
    	  List<Book> books = dao.findByUserId("03590914-a934-4da9-ba4d-b41799f917d1");
    	  
    	  for(Book book:books){
    		  System.out.println(book);
    		  
    		
    	  }
      }
      @Test
      //测试找在cn_notebook中找userid
      public void test_addBook(){
    	 
    	  Book book = new Book();
    	  book.setCn_user_id("03590914-a934-4da9-ba4d-b41799f917d1");
    	  
    	  UUID bookId=UUID.randomUUID();//随机生成唯一笔记本id
    	  book.setCn_notebook_id(bookId.toString().replace("-", ""));
    	  
    	  book.setCn_notebook_name("新建测试");
    	  
    	  book.setCn_notebook_type_id("2");
    	  
    	  Timestamp time = new Timestamp(System.currentTimeMillis());
    	  book.setCn_notebook_createtime(time);
    	  
    	  int rows =dao.save(book);
    	  
    	 System.out.println("受到影响的行数: "+rows);
    	 System.out.println("插入的信息："+book);
      }
}
