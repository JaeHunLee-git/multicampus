package com.sds.mvcframerwork.movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcframerwork.controller.Controller;
import com.sds.mvcframerwork.movie.model.MovieManager;

//영화에 대한 판단 요청을 처리하는 컨트롤러  jsp로도 컨트롤러를 정의할 수 있지만, 
//javaEE의 개발 패턴 중 하나인 model2 방식에 의하면 jsp는 View로써 사용되는게 더 효율적임
//왜?? jsp는 html과 섞어 쓸 수 있으니깐...
public class MovieController implements Controller{
	MovieManager manager = new MovieManager();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//파라미터 받기 
		request.setCharacterEncoding("utf-8");
		String movie = request.getParameter("movie");
		
		//컨트롤러는 절대 직접 일하지 않는다. 왜? 모델 영역인 로직은 언제나 재사용성을 염두해두어야 
		//하므로, 별도의 순수 자바로 빼놓자!!(시간==돈, 재사용성을 높이기 위해)
		
		//3단계: 알맞는 로직 객체에게 일 시킨다
		String msg = manager.getAdvice(movie); //로직 수행
		
		//msg 결과를 View 페이지에서 보여주기  이때 msg는 또 다른 서블릿인 jsp에게 전달되어야 하므로,
		//현재 서블릿에서 응답을 하지 말고, 결과 view로 포워딩 시키자!
		request.setAttribute("msg", msg); //4단계) 결과 저장(jsp에서 보여줄게 있을때...)
		
		//5단계: 결과 페이지 보여주기 
		RequestDispatcher dis = request.getRequestDispatcher("/model2/movie/result.jsp");
		dis.forward(request, response); //포워딩 시작!!! (기존의 request, response 가지고 간다..)
		
	}
}








