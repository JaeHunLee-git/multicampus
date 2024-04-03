package com.sds.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//테스트 요청을 처리하는 하위 컨트롤러 ,spring 2.xx 버전대에서는 모든 컨트롤러는 Controller 인터페이스를
//상속받아야 했다..하지만 그 이후 버전부터는 POJO를 추구하는 정책으로, 더이상 Controller를 강요하지 않고
//선택사항 이다..
public class TestController implements Controller{
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TestController의 handleRequest() 메서드 호출");
		
		//3단계: 알맞는 모델 객체에 일 시키기 
		
		//4단계: jsp로 가져갈 것이 있을 경우 결과 저장
		//request.setAttribute("msg", "테스트 결과 데이터입니다");
		
		//ModelAndView의 Model은 저장소 역할도 수행하며, View는 어떤 페이지를 보여줘야할지 이름을 가질 수 있다. 
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg","테스트입니다"); //request.setAttribute("msg","") 와 동일
		
		//DispatcherServlet에게 반환됨..이때 DispatcherServlet은 InternalResourceViewResolver
		//에게 url해석을 맡기면, 이 객체는 접두어+접미어 조합을 통해 드디어 jsp의 풀 경로를 
		//DispatcherServlet에게 전달
		mav.setViewName("board/result"); //뷰의 이름만을 반환한다
		
		return mav; //DispatcherServlet에게 반환 됨
	}

}
