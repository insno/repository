package com.tedu.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.tedu.pojo.Emp;


// mybatis增删改查


public class TestMyBatis2{

	//1.查询员工表中的所有信息 将所有员工信息封装到list集合 并输出list集合
	//新增一个新员工马云  ceo 7000
	private SqlSessionFactory factory;
	@Before
	public void bef() throws IOException {
		//1读取Mybatis核心配置文件（sqlMapConfig.xml）
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		
		//2通过配置信息获取SqlSessionFactory对象
	 factory = new SqlSessionFactoryBuilder().build(in);	
	}
	//新增一个新员工 马云  CEO  7000
	@Test
	public void testInsert() {
		
		//1获取sqlSession对象
		SqlSession session = factory.openSession();
		//2执行新增sql语句 添加一个新员工
		int rows = session.update("com.tedu.pojo.EmpMapper.insert");
		//3输出结果
		System.out.println("影响的行数"+rows);
		//4移交事务
		session.commit();
	}
	@Test
	public void testUpdate() {
		//1获取SqlSession对象
		SqlSession session = factory.openSession();
		//2执行修改sql语句 修改员工信息
		int rows = session.update("com.tedu.pojo.EmpMapper.update");
		//3输出结果
		System.out.println("影响的行数"+rows);
		//4提交事务
		session.commit();
	}
	@Test
	public void testDelete() {
		//1获取sqlSession对象
		SqlSession session = factory.openSession();
		//2执行sql语句 删除员工信息
		int rows = session.update("com.tedu.pojo.EmpMapper.delete");
		//3输出结果
		System.out.println("影响行数"+rows);		
		//4提交事务
		session.commit();
	}
	@Test
	public void testFindById() {
		//1获取sqlSession对象
		SqlSession session = factory.openSession();
		//2执行查询sql语句 根据ID查询员工信息		
		Emp emp = session.selectOne("com.tedu.pojo.EmpMapper.findById",1);
		//3输出员工信息
		System.out.println(emp);
	}	
}