package test.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud_note.dao.UserDAO;
import com.cloud_note.entity.User;
import com.cloud_note.util.NoteUtil;

public class TestUserDAO{
       private UserDAO dao;
       @Before
       public void init(){
    	   ApplicationContext ac = new
    			   ClassPathXmlApplicationContext(
    			   		"/conf/spring-mybatis.xml");
    	   dao = ac.getBean("userDAO",UserDAO.class);
    			  
       }
       @Test
       //≤‚ ‘≤È—Ø(µ«¬º)
       public void test1(){
    	   User user = dao.findByName("zhouj");
    	   System.out.println(user);
       }
       
       @Test
       //≤‚ ‘◊¢≤·(≤Â»Î)
       public void test2(){
    	   User user = new User();
    	   user.setCn_user_id(NoteUtil.createdId());
    	   user.setCn_user_name("007");
    	   user.setCn_user_nick("666");
    	   user.setCn_user_password("123456");
    	   user.setCn_user_token(null);
    	   dao.save(user);
    	   System.out.println(user);
       }
}
