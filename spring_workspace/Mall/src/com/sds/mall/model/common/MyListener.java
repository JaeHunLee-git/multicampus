package com.sds.mall.model.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;

//톰켓 서버가 가동될때 무언가 하고 싶은 작업이 있을때, 이 리스너를 통해 그 타이밍을 잡아내어 
//원하는 코드를 작성할 수 있다. 
public class MyListener implements ServletContextListener{
	//스프링 컨테이너 
	ApplicationContext context;
	
	
	//톰켓이 가동되면서 어플리케이션이 초기화될때 아래의 메서드가 호출   
	public void contextInitialized(ServletContextEvent sce) {
		//sce 를 이용하여  appcation 내장 객체인 ServletContext 를 얻자 
		ServletContext context = sce.getServletContext();
		String path = context.getInitParameter("contextConfigLocation");
		
		
		System.out.println("톰켓 가동과 함께 어플리케이션 시작 됨 그리고 xml을 스프링 객체가 읽도록 처리 "+path);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("톰켓 종료와 함께 어플리케이션 시작 종료");
	}
	
}
