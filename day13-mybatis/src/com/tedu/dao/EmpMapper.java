package com.tedu.dao;

import java.util.List;

import com.tedu.pojo.Emp;

/**
 * EmpMapper.xml对应的接口
 * com.tedu.dao.EmpMapper
 */
public interface EmpMapper {
	//查询emp表中所有员工信息
	public  List<Emp> findAll();
	
	//根据ID查询员工信息
	public Emp findById(Integer id);
	
	//根据ID修改员工信息
	public int updateById(Emp emp);
	
	
}
