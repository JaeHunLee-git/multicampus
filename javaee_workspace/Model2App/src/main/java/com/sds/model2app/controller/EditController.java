package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.model2app.domain.Board;
import com.sds.model2app.model.board.BoardDAO;

//수정 요청을 처리하는 하위 컨트롤러 
public class EditController implements Controller{
	BoardDAO boardDAO=new BoardDAO();
	String viewName;
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞는 모델 객체에 일 시키기 
		//파라미터 4개 받기 (한글 인코딩 처리는 이미 필터에서 처리완료)
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		String board_idx=request.getParameter("board_idx");
		
		System.out.println("title is "+title);
		System.out.println("writer is "+writer);
		System.out.println("content is "+content);
		System.out.println("board_idx is "+board_idx);
		
		//파라미터를 DTO 담기
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setBoard_idx(Integer.parseInt(board_idx));
		
		//수정 시키기...
		int result = boardDAO.update(board);
		
		if(result>0) {
			//상세 페이지 보여주기 
			//재 접속 시  '/board/detail.do?board_idx=5' uri는 매핑에 적용할 수 없기 때문에 , 요청을
			//유지한 채로 content.jsp에 포워딩한다 
			request.setAttribute("board", board);
			viewName="/view/board/update";
		}else {
			//에러 페이지 보여주기 ( 포워딩) 
			request.setAttribute("msg", "글등록 에러");
			viewName="/view/board/error";
		}
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public boolean isForward() {
		return true;
	}
	
}
