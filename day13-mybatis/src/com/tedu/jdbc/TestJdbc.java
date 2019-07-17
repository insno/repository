package com.tedu.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tedu.pojo.Emp;

public class TestJdbc {
	public static void main(String[] args) {
		/* 查询emp表中的所有员工信息，将每个员工信息的封装到一个
		 * Emp对象中，再将封装了员工信息所有Emp对象存入List集合
		 * 中，并遍历输出所有的员工信息 
		 */
		List<Emp> empList = findAll();
		for(Emp emp : empList){
			System.out.println(emp);
		}
	}
	
	/**
	 * 查询emp表中的所有员工信息,封装到List集合并返回
	 */
	private static List<Emp> findAll() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			//1.注册数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获取数据库连接(Connection)
			conn = DriverManager.getConnection(
					"jdbc:mysql:///yonghedb", 
					"root", "tarena");
			//3.获取传输器
			stat = conn.createStatement();
			//4.利用传输器发送sql到数据库执行,并返回执行结果
			String sql = "select * from emp";
			rs = stat.executeQuery(sql);
			//5.处理结果
			//5.1.声明List集合,用于封装所有的员工信息
			List<Emp> empList = new ArrayList();
			//5.2.遍历ResultSet结果集
			while(rs.next()) {
				//5.3.获取结果集中的每一条员工信息
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String job = rs.getString("job");
				double salary = rs.getDouble("salary");
				//5.4.将每一条员工信息封装到一个Emp对象中
				Emp emp = new Emp();
				emp.setId(id);
				emp.setName(name);
				emp.setJob(job);
				emp.setSalary(salary);
				//5.5.将Emp对象存入List集合中
				empList.add(emp);
			}
			return empList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询失败!");
		} finally{
			//6.释放资源
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					rs = null;
				}
			}
			if(stat != null){
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					stat = null;
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					conn = null;
				}
			}
		}
		return null;
	}

}
