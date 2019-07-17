package com.tedu.pojo;

public class Emp {
	
		//声明私有属性
		private Integer id;		//员工编号
		private String name;	//员工姓名
		private String job;		//员工职位
		private Double salary;	//员工薪资
		
		//提供getter和setter方法
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getJob() {
			return job;
		}
		public void setJob(String job) {
			this.job = job;
		}
		public Double getSalary() {
			return salary;
		}
		public void setSalary(Double salary) {
			this.salary = salary;
		}
		
		//提供toString方法
		@Override
		public String toString() {
			return "Emp [id=" + id + ", name=" + name + ", job=" + job + ", salary=" + salary + "]";
		}
	}


