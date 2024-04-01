package com.sds.mvcproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcproject.notice.model.NoticeDAO;

//게시판의 목록요청을 처리하는 하위 컨트롤러
public class ListController implements Controller{
										/* is  a  */
	NoticeDAO noticeDAO= new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//3단계: 알맞는 모델 객체에게 일 시키기
		//모델 객체에게 일 시킨 후 반환 받은 noticeList는 게시물 목록 이므로, 
		//MVC 중 디자인 역할의 View로 전달되어야 함...View는 jsp가 하므로, 
		//어떻게 해서건 noticeList가 죽지 않고 jsp까지 도달되어야 함..
		List noticeList = noticeDAO.selectAll();
		
		
		//4단계:  뷰 페이지로 가져갈 데이터가 있을 경우 결과 저장 (request에 저장) 
		//아직 응답하지 않았다면  request, response가 살아있으므로, 
		//request 객체에 noticeList을 담아서, View 담당인 jsp에게 포워딩하자!!
		//포워딩을 담당하는 객체인 RequestDispatcher 객체를 얻기!
		request.setAttribute("noticeList", noticeList); //포워딩 시 요청객체가 살아있으므로, 
		//즉 기존 요청 시 생성된 객체가 여전히 유지되고 있으므로, 이 객체에 원하는 데이터를 심어놓자
		//RequestDispatcher dis = request.getRequestDispatcher("/notice/list.jsp"); //포워딩 할 주소
		//dis.forward(request, response); //포워딩 시작
		
		
	}
		
	//목록 요청 시, 보여줄 페이지의 이름 (주의 실제 jsp명을 쓰지 않고, 매핑파일에서의 key값을 사용하자)
	public String getViewName() {
		return "/view/notice/list";//대표 컨트롤러가 이 문자열을 이용할 수 있도록...
	}
	
	@Override
	public boolean isForward() {
		return true;//요청을 유지한다는뜻, 즉 포워딩이 필요함
	}
}
