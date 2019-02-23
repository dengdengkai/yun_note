package basetest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//重复写加载配置文件太麻烦，提出来写成一个抽象类，以后的继承即可,
//此时写这个的原因是便于继承的类拿到一个contextApplication实例
public abstract class TestBase{
	public ApplicationContext getContext(){
		/*启动容器并加载相关配置文件****/	
		//注意多个配置文件加载情况 
		String[] confs ={"conf/spring-mvc.xml",
				         "conf/spring-mybatis.xml",
				         "conf/spring-aop.xml",
				         "conf/spring-transaction.xml"};
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(confs);
		return ac;
	}
}
