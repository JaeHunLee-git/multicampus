package com.sds.mvcframerwork.blood.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcframerwork.blood.model.BloodManager;
import com.sds.mvcframerwork.controller.Controller;

/*
 * MVC 패턴(개발방법론)을 JavaEE로 구현한 개발 방식을 가리켜 Model2 방식이라 한다 
 * 
 * Model 2 개발방법
 * 1) M : 순수 자바 클래스( POJO)
 * 2) V : JSP 
 * 3) C : 클라이언트 요청을 받을 수 있어야 하고 + 웹서버에서 실행될 수 있어야 하고 + 자바의 기술을 이해
 * 	         위 3조건을 만족하는 기술은 서블릿밖에 없다(jsp도 할 수는 있지만 이미 View 에 사용되고 있으므로 제외) 
 * */
public class BloodController implements Controller{
	BloodManager manager=new BloodManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String blood = request.getParameter("blood");
		String msg=null;
		msg = manager.getAdvice(blood); // 모델 객체에게 일 시키기!!
		
		//현재 서블릿이 out.print() 로 결과를 보여줘도 되지만, 만일 그렇게 해버리면
		//컨트롤러로써 역할을 수행할 이 서블릿이 디자인도 담당하게 되므로, 모델2가
		//아닌 다시 모델1방식이 되어 버린다..따라서 결과는 철저히 jsp 파일에서 보여
		//줘야 한다.. 즉 Controller와 View 철저히 나누어서 개발해야 한다.. 
		//현재 우리는 msg 변수값을 어떻게 해서든 result.jsp로 전달을 해줘야 하는데
		//사실상 서블릿이 또 다른 서블릿인 jsp에게 직접적으로 데이터를 전달하는 방법
		//application 내장 객체 또는 세션 또는 XXXX 를 이용하지 않으면 불가능하다..
		
		//이 요청과 관련된 세션을 얻는다, 세션은 특정조건(일정시간 無방문, 브라우저닫기)에
		//해당되지만 않으면, 요청이 끊은 후 다시 재접속 해도 여전히 사용가능하다..
		
		//세션은 자바의 컬렉션 프레임웍 중  Map  을 상속받기 때문에 key-value의 
		//쌍으로 데이터를 모아서 처리함 
		
		//세션보다도 훨씬 오래 scope 범위가 넓은 (오래 사는 객체)  application 내장객체에 담아보자 
		//ServletContext application=request.getServletContext();
		//application.setAttribute("msg", msg);
		
		//HttpSession session=request.getSession();
		//session.setAttribute("msg", msg);
		
		//현재 요청에 대해 응답을 바로 하지말고, 서버상에 존재하는 또 다른 서블릿 or jsp(서블릿)
		//에게 요청을 전달해보자, 응답을 하지 않는 동안인 request, response는 죽지 않으므로, 
		//전달할 데이터가 있다면 request에게 심어놓자!!
		request.setAttribute("msg", msg);

		//누가 결과를 보여줄지 결정 , 즉 어떤 파일을 View로 할지 결정	
		//response.sendRedirect("/model2/blood/result.jsp");  // location.href="" 를 응답정보로 보내는 것과 같다
		//클라이언트 웹브라우저로 하여금 지정한 url로 재접속하라는 뜻
		
		//포워딩을 위한 객체가 바로 RequestDispatcher 이다 
		RequestDispatcher dis = request.getRequestDispatcher("/model2/blood/result.jsp");
		dis.forward(request, response);		
	}
}






