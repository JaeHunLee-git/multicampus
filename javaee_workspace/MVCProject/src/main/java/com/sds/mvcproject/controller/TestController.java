package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//이미지 클라이언트의 요청을 대표 컨트롤러인 DispatcherServlet에서 받고 있기 때문에, 
//하위 컨트롤러 들은 굳이 서블릿 일 필요가 없다, 단 요청을 처리해야 하므로, request, response는
//넘겨 받자!!
public class TestController implements Controller{
										/* is  a  */
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TestController 동작");
	}
}




