package com.company.strudent;

public class Student {
	private String name;
	private String age;
	private String gredeName;
	private String classNum;
	public Student(String name, String age, String gredeName, String classNum) {
		super();
		this.name = name;
		this.age = age;
		this.gredeName = gredeName;
		this.classNum = classNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGredeName() {
		return gredeName;
	}
	public void setGredeName(String gredeName) {
		this.gredeName = gredeName;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
}
