package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basetest.TestBase;

import com.cloud_note.entity.User;
import com.cloud_note.service.UserService;
import com.cloud_note.util.NoteResult;

public class TestUserService{
	 
	private UserService service;
	
	NoteResult<User> result ;
	
/***初始化，先启动容器并加载相关配置文件****/
	@Before
     public void init(){
		//注意多个配置文件加载情况 
				String[] confs ={"conf/spring-mvc.xml",
						         "conf/spring-mybatis.xml",
						         "conf/spring-transaction.xml"};
				ApplicationContext ac = 
						new ClassPathXmlApplicationContext(confs);
       service = ac.getBean("userService",UserService.class);
  			  
     }
/****************登录测试*********/   
	@Test
     //测试用户不存在
     public void test1(){
  	    result = service.checkLogin("654321", "123456");
  	
  	    System.out.println(service.getClass().getName());
  	  /*  System.out.println(result.getStatus());
  	    System.out.println(result.getMsg());
  	  System.out.println(result.getData());
  	  */
     }
     @Test
     //测试密码错误
     public void test2(){
    	 result = service.checkLogin("zhouj", "123456");
    	 System.out.println(result.getStatus());
    	 System.out.println(result.getMsg());
     }
     @Test
     //测试 用户密码正确
     public void test3(){
    	 result = service.checkLogin("zhouj", "55587a910882016321201e6ebbc9f595");
    	 System.out.println(result.getStatus());
    	 System.out.println(result.getMsg());
     }
 /*************注册测试*************************/    
     @Test
     //测试用户已存在 注册失败
     public void test4(){
    	 result = service.addUser("zhouj", "123456", "zj");
    	 System.out.println(result.getStatus());
    	 System.out.println(result.getMsg());
    	 System.out.println(result.getData());
     }
     
     @Test
     //测试用户可以注册,并注册成功
     public void test5(){
    	 result = service.addUser("002", "123456", "zj");
    	 System.out.println(result.getStatus());
    	 System.out.println(result.getMsg());
    	 System.out.println(result.getData());
     }
     
}
