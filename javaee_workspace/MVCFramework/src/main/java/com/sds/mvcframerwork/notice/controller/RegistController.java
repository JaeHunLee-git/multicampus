package com.sds.mvcframerwork.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.mvcframerwork.controller.Controller;
import com.sds.mvcframework.notice.model.Notice;
import com.sds.mvcframework.notice.model.NoticeDAO;

public class RegistController implements Controller{
	NoticeDAO noticeDAO=new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//컨트롤러의 5대 업무 중 3단계 : 알맞는 로직 객체에 일 시킨다
		Notice notice  = new Notice();
		request.setCharacterEncoding("utf-8");
		
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);
		
		//성공이면, 목록 페이지로 재접속 유도
		System.out.println(result);
	}

}



