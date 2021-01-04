package com.company.strudent;

public class StudentInfo {
	private Student student; //클래스 객체를 멤버변수로 가지고 있음
	
	//초기화를 사용하기 위해선 => 생성자, setter
	public StudentInfo(Student student) {
		super();
		this.student = student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	
	public void getStudentInfo() {
		if(student != null) {
			System.out.println("이름 : "+student.getName());
			System.out.println("나이 : "+student.getAge());
			System.out.println("학년 : "+student.getGredeName());
			System.out.println("반 : "+student.getClassNum());
		}
	}


}
