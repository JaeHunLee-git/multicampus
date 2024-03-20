package com.sds.project0319.test;

import javax.servlet.http.HttpServlet; //jsp대신 서버에서 요청을 처리하는 서블릿 클래스 
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest; // 요청 객체 
import javax.servlet.http.HttpServletResponse; //응답 객체 
import java.io.PrintWriter;

//이 클래스는 로컬 pc가 아니라, 오직 서버측에서만 가동 될 수 있는 
//클래스이며, 이러한 목적의 javaEE기반의 클래스를 가리켜 서블릿(Servlet)이라 한다
public class MyServlet extends HttpServlet{
	//이 클래스는 클라이언트인 웹브라우저가 요청을 시도할때, 동작되는 클래스이며, 
	//기존의 jsp가 하던 역할을 그대로 수행한다 


	//이 메서드는 HttpServlet이 보유한 doXXX형 메서드 중 하나로써, 클라이언트가 
	//get방식으로 요청을 시도하면 동작하는 메서드이다..그리고 우측의 throws는 
	//try~catch문을 개발자가 처리하고 싶지않을때, 이 메서드 호출자에게  예외처리를
	//전가(책임전가)시키는 방법이다..
	//또한 아래의 메서드는 클라이언트의 요청을 처리해야 하므로,  jsp에서의 request와 response
	//아래의 메서드에서도 동일하게 필요하게 된다..따라서 매개변수로 넣어주자 
	//jsp에서의 request 내장객체의 자료형 : HttpServletRequest 였다.
	//jsp에서의 response 내장객체의 자료형 : HttpServletResponse 였다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//클라이언트의 웹브라우저에 출력할 메시지 등록 
		//서블릿은 클래스기반으로 개발자가 일일이 코드를 작성해야 하므로, 내장객체 따위는 지원되지
		//않는다!!
			
		//jsp 에서의 page  지시영역에서 명시된  contentType과 charset을 여기서도 진행해야함
		response.setContentType("text/html"); // contentType="text/html;"
		response.setCharacterEncoding("utf-8"); //contentType=";charset=utf-8"

		//응답 객체가 보유한 출력 스트림을 얻기
		PrintWriter out=response.getWriter();	

		//응답객체가 보유한 출력 스트림에 문자열을 누적시켜놓자!
		out.print("나의 첫 Servlet<br> success !!");
	}

	//이 메서드는 클라이언트가 post 방식으로 요청을 시도할때 동작함 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html"); //jsp의 contentType="text/html;" 동일
		response.setCharacterEncoding("utf-8"); //jsp의 contentType=";charset=utf-8" 동일
		
		//파라미터의 인코딩 처리 
		request.setCharacterEncoding("utf-8");

		//클라이언트가 전송한 파라미터를 받아보자 
		String title = request.getParameter("title");
		
		PrintWriter out = response.getWriter(); //response 객체로부터 스트림 얻기			
		out.print("당신이 전송한 제목은 "+title); //여기서 웹브라우저에 출력이 발생하는게 아니라
												//단지 response 객체가 보유한 출력 스트림에 , 추후 
												//톰켓이 생성할 html 컨텐츠에 사용될 문자열을 모으는 것임
	}
}
