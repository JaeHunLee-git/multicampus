package com.sds.springapp.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class UseCook {
	public static void main(String[] args) {
		//스프링의 설정 파일인 context.xml 을 읽어들여, 해석 한 후 빈 객체들을 관리해주는 스프링의 컨테이너를
		//ApplicationContext 라 한다 
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("com/sds/springapp/spring/context.xml");
		
		
		//요리사를 생성하여 일을 시키자 
		Cook cook = (Cook)context.getBean("cook"); //스프링컨테이너가 보유한 빈을 얻자;
		cook.makeFood();
	}
}
