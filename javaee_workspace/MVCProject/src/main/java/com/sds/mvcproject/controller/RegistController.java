package com.sds.mvcproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.Notice;
import com.sds.mvcproject.notice.model.NoticeDAO;

//글쓰기 요청을 처리하는 하위 컨트롤러 
public class RegistController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 알맞는  모델 객체에게 일 시키기 
		
		//파라미터 받기
		//request.setCharacterEncoding("utf-8");//파라미터 문자 깨지지 않도록
		
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		Notice notice =new Notice(); //empty 상태 DTO 생성
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//아래의 result 는 메서드 수행 성공 여부를 판단하는 기준 값일 뿐, 디자인 페이지에서 처리할 
		//디자인적 데이터가 아니다~~! 
		int result = noticeDAO.insert(notice);
		
		//성공인 경우는 리스트를 보여주고,
		
		//글 작성 후, 목록처럼 가져갈 것이 따로 있지 않음에도 불구하고 여전히 포워딩을 해야할까???
		//만일 모든 요청마다 포워딩으로 처리하면 어떤 문제가 생기는지 경험해보자
		if(result>0) {
			//request.setAttribute("", notice);//결과저장할 것이 없다
			System.out.println("등록 성공");
			//RequestDispatcher dis=request.getRequestDispatcher("/notice/list.jsp"); 
			//dis.forward(request, response);//포워딩
			//클라이언트로 하여금, 목록을 요청하도록 명령 
			//response.sendRedirect("/board/list.do");//지정한 url로 재접속을 유도...
		}else {
			//실패인 경우엔 실패 메세지..
			System.out.println("등록 실패");
		}
	}
	
	//글쓰기 후, 보여질 뷰페이지 매핑 값 반환
	public String getViewName() {
		return "/view/notice/regist";
	}

	public boolean isForward() {
		return false;
	}
}





