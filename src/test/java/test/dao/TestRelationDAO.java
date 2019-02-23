package test.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import basetest.TestBase;

import com.cloud_note.dao.RelationDAO;
import com.cloud_note.entity.Book;
import com.cloud_note.entity.User;

public class TestRelationDAO extends TestBase{
       private RelationDAO rdao;
       @Before
       public void init(){
    	  ApplicationContext ac = super.getContext();
    	  rdao = ac.getBean("relationDAO",RelationDAO.class);
       }
       
       @Test
       //测试两个SQL语句 
       public void TestMany1(){
    	   String userId="9f620b5d893641259d5fda507c2b5ab7";
    	    User user = rdao.findUserAndBooks(userId); 
         System.out.println("=========用户信息=========");
         System.out.println("用户名字："+user.getCn_user_name());
         System.out.println("用户昵称："+user.getCn_user_nick());
         System.out.println("笔记本数量："+user.getBooks().size());
         System.out.println("=======笔记本列表===========");
          for(Book book :user.getBooks()){
        	  System.out.println(book.getCn_notebook_name());
          }
       }
       @Test
       //测试1条SQL语句
       public void Testmany2(){
    	   String userId="9f620b5d893641259d5fda507c2b5ab7";
    	    User user = rdao.findUserAndBooks1(userId); 
         System.out.println("=========用户信息=========");
         System.out.println("用户名字："+user.getCn_user_name());
         System.out.println("用户昵称："+user.getCn_user_nick());
         System.out.println("笔记本数量："+user.getBooks().size());
         System.out.println("=======笔记本列表===========");
          for(Book book :user.getBooks()){
        	  System.out.println(book.getCn_notebook_name());
          }
       }
       
       @Test
       //测试2条SQL语句
       public void TestSingle1(){
    	   List<Book> list=rdao.findBookAndUser();
    	   for(Book book:list){
    		   System.out.println(
    				   book.getCn_notebook_name()
    				   +" "+book.getCn_notebook_createtime()
    				   );
    		   if(book.getUser()!=null){
    			   System.out.println(book.getUser().getCn_user_name());
    		   }
    	   }
       }
       
       @Test
       //测试1条SQL语句
       public void TestSingle2(){
    	   List<Book> list=rdao.findBookAndUser1();
    	   for(Book book:list){
    		   System.out.println(
    				   book.getCn_notebook_name()
    				   +" "+book.getCn_notebook_createtime()
    				   );
    		   if(book.getUser()!=null){
    			   System.out.println(book.getUser().getCn_user_name());
    		   }
    	   }
       }
}
