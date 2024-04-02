package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.model2app.domain.Board;
import com.sds.model2app.model.board.BoardDAO;

//상세보기 요청을 처리하는 하위 컨트롤러  
public class DetailController implements Controller{
	BoardDAO boardDAO  = new BoardDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞는 모델 객체에 일 시킨다 
		int board_idx =Integer.parseInt(request.getParameter("board_idx"));
		
		Board board=boardDAO.select(board_idx);
		
		//4단계: jsp로 가져갈 데이터가 있을 경우 request 저장
		request.setAttribute("board", board);
	}

	public String getViewName() {
		return "/view/board/detail";
	}

	public boolean isForward() {
		return true;
	}
	
}
