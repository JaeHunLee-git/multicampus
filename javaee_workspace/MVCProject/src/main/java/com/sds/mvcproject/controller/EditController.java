package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.Notice;
import com.sds.mvcproject.notice.model.NoticeDAO;

//수정 요청을 처리하는 하위 컨트롤러 
public class EditController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//파라미터 받기
		//request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		int notice_idx=Integer.parseInt(request.getParameter("notice_idx")) ;
		
		
		//3단계: 알맞는 모델에게 일 시킨다
		Notice notice = new Notice();//empty DTO 생성 
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		notice.setNotice_idx(notice_idx);
		
		int result = noticeDAO.update(notice);
		
		//수정 후, 상세보기를 재접속하도록 유도해야 하지만,  예를 들어 /board/detail.do?notice_idx=7
		//URL을 구성할 경우, ??notice_idx=7 문자열이 매핑 파일에 존재하지 않는 문자열이기 때문에 
		//매핑에 실패하게 된다..
		//해결책? 요청을 유지하면서 즉 포워딩하여 DTO를 가지고 가자 content.jsp로... 
		//포워딩 처리하자 
		request.setAttribute("notice", notice);
		//RequestDispatcher dis = request.getRequestDispatcher("/notice/content.jsp"); //상세페이지로 포워딩
		//dis.forward(request, response); //포워딩 시작
	}
	
	//글 수정 후, 보여질 페이지에 대한 매핑 키 값 반환
	public String getViewName() {
		return "/view/notice/detail";
	}
	
	public boolean isForward() {
		return true;
	}
}







