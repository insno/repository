package com.tedu.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.tedu.pojo.Emp;

//测试#号占位符
public class TestMyBatis3 {
	private SqlSessionFactory factory;

	@Before
	public void bef() throws IOException {
		//1读取mybatis的核心配置文件
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2通过配置信息获取工厂对象
		factory = new SqlSessionFactoryBuilder().build(in);
	}
	//新增往emp表中添加一个新员工
	@Test
	public void testInsert(){
		//获取sqlsession对象
		SqlSession session = factory.openSession();		
		//2执行新增sql语句
		//将参数封装到emp对象中
		Emp emp = new Emp();
		emp.setName("刘备");
		emp.setJob("java工程师");
		emp.setSalary(1500.0);
		int rows = session.update("com.tedu.pojo.EmpMapper.insert2",emp);
		//3输出结果
		System.out.println("影响的行数"+rows);
		//4提交事务
		session.commit();
	}
	//将员工刘备的员工信息修改为架构狮
	//薪资改为25000
	@Test
	public void testupdate(){
		//获取sqlsession对象
		SqlSession session = factory.openSession();
		//执行sql语句修改员工信息
		Emp emp = new Emp();
		emp.setName("刘备");
		emp.setJob("架构狮");
		emp.setSalary(2500.0);
		int rows = session.update("com.tedu.pojo.EmpMapper.update2",emp);
		//输出结果
		System.out.println("影响的行数"+rows);
		//提交事务
		session.commit();
	}
	//删除name为刘备的员工信息
	@Test
	public void testDelete(){
		//获取sqlsession对象
		SqlSession session = factory.openSession();
		//2执行sql语句
		int rows = session.update("com.tedu.pojo.EmpMapper.delete2");
		//输出结果
		System.out.println("输出结果"+rows);
		//提交事务
		session.commit();
	}
	//查询薪资在2500到3500之间的员工信息
	@Test
	public void testFindAll(){
		//获取sqlsession对象
		SqlSession session = factory.openSession();
		//执行sql语句
		Map map = new HashMap();
		map.put("minSal",2500);
		map.put("maxSal",3500);
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapper.findAll2",map);
		//输出结果
		for(Emp emp :list) {
			System.out.println(emp);

		}
	}
	/*#()占位符和$()占位符的区别
	 * 在大都数情况下都是用#()占位符
	 * $()占位符通常是为不带引号的字符串进行占位
	 * 
	 */

	//10查询emp表中所有员工信息
	@Test
	public void testFindAll3() {
		//1获取sql对象
		SqlSession session = factory.openSession();
		//2执行查询sql获取所有员工信息
		//${}占位符对应的值必须封装到map中
		Map map = new HashMap();
		map.put("cols","id,name,job");		
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapeer.findAll3",map);
		//3输出结果
		for(Emp emp :list) {
			System.out.println(emp);

		}

	}
	//根据name模糊查询
	@Test
	public void testFindAll4() {
		//1获取sql对象
		SqlSession session = factory.openSession();
		//2执行查询sql获取所有员工信息
		//${}占位符对应的值必须封装到map中
		Map map = new HashMap();
		map.put("name","刘");		
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapeer.findAll4",map);
		//3输出结果
		for(Emp emp :list) {
			System.out.println(emp);

		}

	}
	//根据name模糊查询
	//单元测试的方法要求必须符合以下条件
	//方法必须是公共的public
	//方法必须是无返回值的vid
	//方法必须是非静态的
	//方法必须是无参的
	@Test
	public void testFindAll5() {
		//1获取sql对象
		SqlSession session = factory.openSession();
		//2执行查询sql获取所有员工信息
		//${}占位符对应的值必须封装到map中
		Map map = new HashMap();
		map.put("name","涛");		
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapeer.findAll5",map);
		//3输出结果
		for(Emp emp :list) {
			System.out.println(emp);

		}		
	}
	//查询所有员工
	@Test
	public void testFindAll6() {
		//1获取sql对象
		SqlSession session = factory.openSession();
		//2执行查询sql获取所有员工信息
		//${}占位符对应的值必须封装到map中
		Map map = new HashMap();
		map.put("minSal",3000);		
		map.put("maxSal",4000);		
		List<Emp> list = session.selectList("com.tedu.pojo.EmpMapeer.findAll6",map);
		//3输出结果
		for(Emp emp :list) {
			System.out.println(emp);

		}		
	}
	//修改员工信息
	@Test
	public void testUpdate3() {
		//1获取sql对象
		SqlSession session = factory.openSession();
		//2执行修改sql员工信息	
		Emp emp = new Emp();
		emp.setName("王海涛");
		emp.setJob("法师");
		emp.setSalary(15000.0);
		int rows = session.update("com.tedu.pojo.EmpMapeer.findAll6",emp);
		//3输出结果
		System.out.println("影响的行数"+rows);
	}
	//修改员工信息
	@Test
	public void testDeleteByIds() {
		//1获取sql对象
		SqlSession session = factory.openSession();
		//2执行sql 删除员工信息	
		//声明一个数组 包含所要删除员工的id
		Integer[] ids= {1,3,5};
		int rows = session.update("com.tedu.pojo.EmpMapeer.deleteByIds",ids);
		//3输出结果
		System.out.println("影响的行数"+rows);
	}
}
