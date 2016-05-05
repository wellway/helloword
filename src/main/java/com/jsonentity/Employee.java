package com.jsonentity;

import java.util.Date;



public class Employee {
	private String id;
	private String name;
	private int age;
	private Date date;
	
	public Employee(String id, String name,int age,Date date2){
		this.id= id;
		this.name=name;
		this.age = age;
		this.date = date2;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
