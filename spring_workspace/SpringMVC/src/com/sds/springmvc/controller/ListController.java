package com.sds.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sds.springmvc.model.board.BoardDAO;

//글 목록 요청을 처리하는 하위 컨트롤러 
public class ListController implements Controller{
	BoardDAO boardDAO=new BoardDAO();
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계: 알맞는 모델에게 일시킨다 
		List boardList = boardDAO.selectAll();
		
		//4단계: jsp에서 보여줄게 있을 경우 결과 저장 , 즉 jsp로 가져갈 것이 있다면 결과 저장 
		ModelAndView mav = new ModelAndView(); //형님 컨트롤러에게 반환됨..
		//형님 컨트롤러가 InternalResourceViewResolver 를 통해  jsp 실제 페이지를 얻게됨 
		mav.addObject("boardList", boardList); //결과 저장  
		mav.setViewName("board/list");
		return mav;
	}
}
