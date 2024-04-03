package com.sds.springapp.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseStudent {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/sds/springapp/spring/context.xml");
		
		Student student = (Student)context.getBean("student");// 스프링컨테이너로부터 얻기
		
		student.gotoSchool();
		student.study();
		student.haveLunch();
		student.goHome();

	}

}
