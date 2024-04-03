package com.sds.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sds.springmvc.domain.Board;
import com.sds.springmvc.model.board.BoardDAO;

//글쓰기 요청을 처리하는 하위 컨틀로러 
public class RegistController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//제목, 작성자, 내용 파라미터 받기
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		int result = boardDAO.insert(board);
		
		//요청을 끊고, 목록 페이지를 재요청 
		ModelAndView mav = new ModelAndView(); 
		mav.setViewName("redirect:/board/list"); //redirect를 명시하면 forwarding이 일어나지 않음
		
		return mav;
	}
}












