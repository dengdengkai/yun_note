package basetest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//�ظ�д���������ļ�̫�鷳�������д��һ�������࣬�Ժ�ļ̳м���,
//��ʱд�����ԭ���Ǳ��ڼ̳е����õ�һ��contextApplicationʵ��
public abstract class TestBase{
	public ApplicationContext getContext(){
		/*����������������������ļ�****/	
		//ע���������ļ�������� 
		String[] confs ={"conf/spring-mvc.xml",
				         "conf/spring-mybatis.xml",
				         "conf/spring-aop.xml",
				         "conf/spring-transaction.xml"};
		ApplicationContext ac = 
				new ClassPathXmlApplicationContext(confs);
		return ac;
	}
}
