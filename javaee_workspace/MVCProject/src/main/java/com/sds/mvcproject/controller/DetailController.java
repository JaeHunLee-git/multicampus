package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.Notice;
import com.sds.mvcproject.notice.model.NoticeDAO;

//상세보기 요청을 처리하는 하위 컨트롤러
public class DetailController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞는 모델 객체에 일 시키기
		int notice_idx = Integer.parseInt(request.getParameter("notice_idx")); //"3" 
		Notice notice = noticeDAO.select(notice_idx);
		
		//4단계: 결과jsp에 가져갈 것이 있다면, 결과 저장 (request 에 저장, 포워딩 필요)
		//notice DTO를  결과 jsp인 content.jsp 까지살려 가야하므로, request에 저장 및 포워딩 처리하자 
		request.setAttribute("notice", notice); //DTO를  request에 저장 
		//RequestDispatcher dis = request.getRequestDispatcher("/notice/content.jsp"); //포워딩 할 주소
		//dis.forward(request, response); //포워딩 시작
	}
	
	//상세보기 요청시 사용할 매핑 key값 
	public String getViewName() {
		return "/view/notice/detail";
	}
	
	public boolean isForward() {
		return true;
	}
	
}





