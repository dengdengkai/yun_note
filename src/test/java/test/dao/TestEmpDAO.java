package test.dao;

import org.junit.Before;
import org.junit.Test;

import basetest.TestBase;

import com.cloud_note.dao.EmpDAO;
import com.cloud_note.entity.Emp;

public class TestEmpDAO extends TestBase{
   
	
	
	public EmpDAO empDao;
	
	@Before
	public void init(){
		empDao=getContext().getBean(
				"empDAO",EmpDAO.class);
	}
	@Test
	public void test(){
		Emp emp = new Emp();
		emp.setAge(88);
		emp.setName("zhangfei");
		empDao.save(emp);
		int id=emp.getId();
		System.out.println(emp);
		System.out.println("刚插入的id:"+id);
	}
}
