package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.NoticeDAO;

//삭제 요청을 처리하는 하위 컨트롤러
public class DeleteController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞는 모델 객체에 일 시킨다
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx"));
		int result = noticeDAO.delete(notice_idx); //삭제처리
		
		//저장할 결과가 없으므로, 포워딩할 이유가 없다..따라서 클라이언트로 하여금 목록을 요청하도록 명령 
		//response.sendRedirect("/board/list.do");
	}
	
	@Override
	public String getViewName() {
		return "/view/notice/delete";
	}
	
	//요청을 유지할 필요 없으며, 새로 접속을 유도해야 한다..
	public boolean isForward() {
		return false;
	}
}





