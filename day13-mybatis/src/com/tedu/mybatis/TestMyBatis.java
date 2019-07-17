package com.tedu.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.tedu.pojo.Emp;


//mybatis快速入门

public class TestMyBatis{
	//  1.查询员工表中的所有信息 将所有员工信息封装到list集合 并输出list集合	
	public static void main(String[] args) throws IOException{
		//1读取Mybatis核心配置文件（sqlMapConfig.xml）
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//2通过配置信息获取SqlSessionFactory对象
		 SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3通过工厂获取SqlSession对象
		 SqlSession session = factory.openSession();
		//4执行查询Sql获取所有员工信息
		 List<Emp> list = session.selectList("com.tedu.pojo.EmpMapper.findAll");
		//5遍历集合输出所欲员工信息
		for(Emp emp:list) {
			System.out.println(emp);			
		}
	}
}
