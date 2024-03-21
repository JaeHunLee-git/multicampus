package com.sds.sessionapp.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//서블릿으로 요청을 처리하기 위해서는 개발자가 doXXX 형 재정의 해야 한다
//요청이 get방식이면 doGET() , post 이면 doPost() 재정의해야 한다 
public class SessionTest extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트의 브라우저에 출력할 문자열을 response 객체가 출력스트림에 모으자
		PrintWriter out = response.getWriter();
		
		//아래의 문자열이 한글이 깨지지 않으려면, jsp에서의  page 지시영역에서 작성한 기능을 
		//똑같이 작성해야 한다.. 
		response.setContentType("text/html;charset=utf-8"); // <%@ page contentType=""%>
		out.print("어서오세요 처음 봴께요");
		
		//클라이언트가 현재 서블릿을 요청 시, 등짝(브라우저 메모리)에 아무것도 즉 쿠기ID없이 접근한다면
		//Tomcat은  session 객체를 생성하고, 이 객체에 할당된 ID를 이용하여 쿠키값으로 응답정보 
		//생성 시 살짝 껴넣는다 
		
		//주의 개발자가 session 객체를  생성하는게 아니라, 이미 생성되어진 session을 얻어오자 
		HttpSession session=request.getSession();//고양이가 이미 생성한 세션을 얻어오기
		
		String sessionID = session.getId(); //고양이가 임의로 생성해  낸 고유 세션ID
		
		System.out.println("현재 요청한 클라이언트가 발급 받은 세션 ID" + sessionID); //서버의 로그에 출력
		
		//클라이언트는 아래의 사항에 해당하지만 않으면 쿠키값이 유지된다.
		//1) 웹브라우저 종료 
		//2) 정해진 시간 동안 요청 않하기  
		
		//서버가 발급한 세션 ID인 쿠키가 유지되는 동안은 서버의 세션 객체를 접근할 수 있으므로, 
		//개발자가 세션 객체에 회원정보를 넣어둔다면, 즉 세션이 유지되는 동안 회원정보도 계속 
		//유지하여 보여줄 수 있다..
		
		//session  객체는 데이터를 Map으로 관리한다. 즉 map의 자식이다
		//java.util 패키지에서 지원하는 컬렉션 프레임웍 3가지 유형 중 map은 객체를 모아서 처리하되, 
		//key -value의 쌍으로 관리한다 
		
		session.setAttribute("name", "KING");
		session.setAttribute("hobby", "프로그래밍");
		session.setAttribute("age", 20);
		
		//클라이언트로 하여금 서버 측의 또 다른 jsp를 요청하도록 유도하자 
		out.print("<script>");
		out.print("location.href='/test/mypage.jsp';");
		out.print("</script>");
	}
}



