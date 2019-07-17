package com.tedu.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.tedu.dao.EmpMapper;
import com.tedu.pojo.Emp;

/*
  	测试mybatisMapper接口开发
	1声明一个接口要求xxx.mapper。xml文件中的namespace的值为接口的全路径名
	2要求接口中方法的名字和sql标签的id值相同
  	3接口中方法的参数类型和sql语句参数类型相同（sql语句的参数类型可以省略）
	4sql标签上的方法类型和接口中方法的返回值也要相同（如果方法返回值值集合 sql标签字需要指定集合中的泛型）
 */
public class TestbatisMapper {
	public static void main(String[] args) throws IOException{
		//1读取Mybatis核心配置文件（sqlMapConfig.xml）
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2通过配置信息获取SqlSessionFactory对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3通过工厂获取SqlSession对象
		SqlSession session = factory.openSession();
		//4执行查询Sql获取所有员工信息
		EmpMapper mapper = session.getMapper(EmpMapper.class);//用父类型接收子类对象
		List<Emp> list = mapper.findAll();
		//com.tedu.dao.EmpMapper.findAll
		//5遍历集合输出所有员工信息
		for(Emp emp:list) {
			System.out.println(emp);			
		}
	}
	/**
	 * 2Mapeer接口开发根据ID查询员工信息
	 * @throws IOException 
	 */
	@Test
	public void testFindById() throws IOException {
		//1读取Mybatis核心配置文件（sqlMapConfig.xml）
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2通过配置信息获取SqlSessionFactory对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3通过工厂获取SqlSession对象
		SqlSession session = factory.openSession();
		//4获取EmpMapper接口的实例
		EmpMapper mapper = session.getMapper(EmpMapper.class);

		Emp emp = mapper.findById(4);
		System.out.println(emp);

	}
	//Mapper接口开发通过ID修改员工信息
	@Test
	public void testUpdateById() throws IOException {
		//1读取Mybatis核心配置文件（sqlMapConfig.xml）
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2通过配置信息获取SqlSessionFactory对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3通过工厂获取SqlSession对象
		SqlSession session = factory.openSession();
		//4获取EmpMapper接口的实例
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		Emp e = new Emp();
		e.setName("王海涛");
		e.setJob("中单");
		e.setSalary(20.0);
		e.setId(4);
		int rows = mapper.updateById(e);
		System.out.println("影响的行数"+rows);
		//提交事务
		session.commit();

	}
}
