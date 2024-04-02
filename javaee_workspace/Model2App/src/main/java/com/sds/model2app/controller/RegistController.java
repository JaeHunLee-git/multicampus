package com.sds.model2app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.model2app.domain.Board;
import com.sds.model2app.model.board.BoardDAO;

//글쓰기 요청을 처리하는 하위 컨트롤러
public class RegistController implements Controller{
	
	BoardDAO boardDAO= new BoardDAO();
	
	String viewName;
	boolean isForward;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞는 모델 객체에 일 시킨다...
		//request.setCharacterEncoding(""); //이미 필터에서 처리했슴!!
		
		Board board = new Board(); //empty 상태 
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
		
		int result = boardDAO.insert(board);
		
		if(result>0) { //글등록 성공이면, 목록을 갱신해서 보여줘야 하므로 /board/list.do로 다시 
							//재접속을 유도해야 함..
			viewName="/view/board/regist";
			isForward=false; //재접속 유도
			
		}else { //실패 시엔, 실패원인을 알수있는 메시지를 저장하여 에러 페이지로 포워딩..
			viewName="/view/board/error";
			String msg="글등록 실패";
			request.setAttribute("msg", msg); //에러 메시지 저장
			isForward=true; //포워딩할 것이므로..
			
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
