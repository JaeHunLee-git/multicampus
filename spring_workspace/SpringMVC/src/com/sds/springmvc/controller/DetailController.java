package com.sds.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sds.springmvc.domain.Board;
import com.sds.springmvc.model.board.BoardDAO;

//상세보기 요청을 처리하는 하위 컨트롤러  
public class DetailController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계: 알맞는 모델에게 일 시킨다 
		String board_idx = request.getParameter("board_idx");
		
		Board board=boardDAO.select(Integer.parseInt(board_idx));
		
		//4단계: 결과페이지로 가져갈 것이 잇다면 결과 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board/content");//이 이름의 해석은 ViewResolver에게 맡기자
		
		return mav;
	}
	
}







