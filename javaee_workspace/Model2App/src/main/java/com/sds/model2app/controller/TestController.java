package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//test.do 요청을 처리하는 컨트롤러
//3단계-알맞는 모델 객체에 일 시키기 
//4단계(선택) - jsp로 가져갈 것이 있을때는 결과를 request  저장 (주로 게시물 목록, 한건 보기 )
public class TestController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//3단계: 생략 
		
		//4단계: 
		request.setAttribute("msg", "모델2 프레임워크 테스트 중");
	}

	@Override
	public String getViewName() {
		return "/view/test";
	}

	@Override
	public boolean isForward() {
		return true;
	}
		
}
