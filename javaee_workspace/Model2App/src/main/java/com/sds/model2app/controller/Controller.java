package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//모든 하위 컨트롤러의 최상의 부모인 인터페이스 정의 
//모든 하위 컨트롤러가 이 자료형으로 통일될 수 있으며, 또한 반드시 구현해야 할 메서드까지 
//구현강제할 수 있으므로, 인터페이스와 추상클래스로 처리하면 되지만, 특히 인터페이스가 더 많이 쓰인다.
//이유? 다중 상속을 피해가기 위해..
public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	//모든 하위 컨트롤러가 반드시 반환해야할 뷰 페이지명 반환
	public String getViewName();
	
	//포워딩할지 리다이렉트 할 지 여부를 반환하는 메서드 정의 
	public boolean isForward();
	
}


