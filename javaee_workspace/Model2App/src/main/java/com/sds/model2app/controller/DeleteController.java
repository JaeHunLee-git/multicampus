package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.model2app.model.board.BoardDAO;

//삭제 요청을 처리하는 하위 컨트롤러 
public class DeleteController implements Controller{
	BoardDAO boardDAO=new BoardDAO();
	String viewName;
	boolean isForward;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞는 모델 객체에 일 시키기 
		
		String board_idx = request.getParameter("board_idx");
		System.out.println("board_idx is "+board_idx);
		
		int result = boardDAO.delete(Integer.parseInt(board_idx));
		
		if(result>0) { 
			//성공의 경우 /board/list.do 를 재접속 하도록 redirect 로 처리, isForward=false
			viewName="/view/board/delete";
			isForward=false; //재접속이므로..
		}else {
			//실패의 경우 에러 메시지 담아서 에러페이지 포워딩
			viewName="/view/board/error";
			request.setAttribute("msg", "삭제 실패");
			isForward=true; //메시지를 살려서 error.jsp까지 가져가야 하므로, 포워딩이 필요함
		}
	}

	@Override
	public String getViewName() {
		return viewName;
	}

	@Override
	public boolean isForward() {
		return isForward;
	}
	
}
